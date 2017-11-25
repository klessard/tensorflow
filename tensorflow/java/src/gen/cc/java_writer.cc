/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

#include <string>
#include <set>
#include <vector>
#include <deque>

#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/java/src/gen/cc/source_writer.h"
#include "tensorflow/java/src/gen/cc/java_defs.h"
#include "tensorflow/java/src/gen/cc/java_writer.h"

namespace tensorflow {
namespace java {
namespace {

void WriteModifiers(int modifiers, SourceWriter* src_writer) {
  if (modifiers & PUBLIC) {
    src_writer->Write("public ");
  } else if (modifiers & PROTECTED) {
    src_writer->Write("protected ");
  } else if (modifiers & PRIVATE) {
    src_writer->Write("private ");
  }
  if (modifiers & STATIC) {
    src_writer->Write("static ");
  }
  if (modifiers & FINAL) {
    src_writer->Write("final ");
  }
}

void WriteType(const JavaType& type, SourceWriter* src_writer) {
  src_writer->Write(Java::IsWildcard(type) ? "?" : type.name());
  if (!type.params().empty()) {
    src_writer->Write("<");
    std::vector<JavaType>::const_iterator it;
    for (it = type.params().cbegin(); it != type.params().cend(); ++it) {
      if (it != type.params().cbegin()) {
        src_writer->Write(", ");
      }
      WriteType(*it, src_writer);
    }
    src_writer->Write(">");
  }
}

void WriteGenerics(const std::vector<const JavaType*>& generics,
    SourceWriter* src_writer) {
  src_writer->Write("<");
  for (std::vector<const JavaType*>::const_iterator it = generics.cbegin();
      it != generics.cend(); ++it) {
    if (it != generics.cbegin()) {
      src_writer->Write(", ");
    }
    src_writer->Write((*it)->name());
    if (!(*it)->supertypes().empty()) {
      src_writer->Write(" extends ");
      WriteType((*it)->supertypes().front(), src_writer);
    }
  }
  src_writer->Write(">");
}

void WriteAnnotations(const std::vector<JavaAnnot>& annotations,
    SourceWriter* src_writer) {
  std::vector<JavaAnnot>::const_iterator it;
  for (it = annotations.cbegin(); it != annotations.cend(); ++it) {
    src_writer->Write("@" + it->type().name());
    if (!it->attrs().empty()) {
      src_writer->Write("(")->Write(it->attrs())->Write(")");
    }
    src_writer->EndOfLine();
  }
}

void WriteDoc(const JavaDoc& doc, const std::vector<JavaVar>* params,
    SourceWriter* src_writer) {
  if (doc.empty() && (params == nullptr || params->empty())) {
    return;  // no doc to print
  }
  bool line_break = false;
  src_writer->Write("/**")->EndOfLine()->LinePrefix(" * ");
  if (!doc.descr().empty()) {
    src_writer->Inline(doc.descr())
        ->EndOfLine();
    line_break = true;
  }
  if (params != NULL && !params->empty()) {
    if (line_break) {
      src_writer->EndOfLine();
      line_break = false;
    }
    std::vector<JavaVar>::const_iterator it;
    for (it = params->begin(); it != params->end(); ++it) {
      src_writer->Write("@param ")->Write(it->name());
      if (!it->doc().descr().empty()) {
        src_writer->Write(" ")->Write(it->doc().descr());
      }
      src_writer->EndOfLine();
    }
  }
  if (!doc.value().empty()) {
    if (line_break) {
      src_writer->EndOfLine();
    }
    src_writer->Inline("@return " + doc.value())->EndOfLine();
  }
  src_writer->RemoveLinePrefix()->Write(" **/")->EndOfLine();
}

}  // namespace

JavaSourceStream& JavaSourceStream::operator<<(const JavaType& type) {
  WriteType(type, src_writer_);
  return *this;
}

JavaClassWriter* JavaClassWriter::Begin(const JavaType& clazz, int modifiers) {
  GenericTypeScanner generics(&declared_generics_);
  clazz.Scan(&generics);
  WriteDoc(clazz.doc(), nullptr, src_writer_);
  if (!clazz.annotations().empty()) {
    WriteAnnotations(clazz.annotations(), src_writer_);
  }
  WriteModifiers(modifiers, src_writer_);
  *this << "class " << clazz.name();
  if (!generics.discoveredTypes().empty()) {
    WriteGenerics(generics.discoveredTypes(), src_writer_);
  }
  if (!clazz.supertypes().empty()) {
    std::deque<JavaType>::const_iterator it = clazz.supertypes().cbegin();
    if (it->kind() == JavaType::CLASS) {  // superclass is always first in list
      *this << " extends " << *it++;
    }
    bool first_inf = true;
    while (it != clazz.supertypes().cend()) {
      *this << (first_inf ? " implements " : ", ") << *it++;
      first_inf = false;
    }
  }
  *this << beginb;
  return this;
}

JavaClassWriter* JavaClassWriter::WriteFields(
    const std::vector<JavaVar>& fields, int modifiers) {
  *this << endl;
  std::vector<JavaVar>::const_iterator it;
  for (it = fields.cbegin(); it != fields.cend(); ++it) {
    WriteModifiers(modifiers, src_writer_);
    *this << it->type() << " " << it->name() << ";" << endl;
  }
  return this;
}

JavaMethodWriter* JavaClassWriter::BeginMethod(const JavaMethod& method,
    int modifiers) {
  *this << endl;
  WriteDoc(method.doc(), &method.args(), src_writer_);
  if (!method.annotations().empty()) {
    WriteAnnotations(method.annotations(), src_writer_);
  }
  JavaMethodWriter* method_writer;
  if (modifiers & STATIC) {
    method_writer = new JavaMethodWriter(src_writer_);
  } else {
    method_writer = new JavaMethodWriter(src_writer_, declared_generics_);
  }
  return method_writer->Begin(method, modifiers);
}

JavaMethodWriter* JavaMethodWriter::Begin(const JavaMethod& method,
    int modifiers) {
  GenericTypeScanner generics(&declared_generics_);
  method.ScanTypes(&generics, false);
  WriteModifiers(modifiers, src_writer_);
  if (!generics.discoveredTypes().empty()) {
    WriteGenerics(generics.discoveredTypes(), src_writer_);
    *this << " ";
  }
  if (!method.type().empty()) {
    *this << method.type() << " ";
  }
  *this << method.name() << "(";
  if (!method.args().empty()) {
    for (std::vector<JavaVar>::const_iterator arg = method.args().cbegin();
        arg != method.args().cend(); ++arg) {
      if (arg != method.args().cbegin()) {
        *this << ", ";
      }
      *this << arg->type() << (arg->periodic() ? "... " : " ") << arg->name();
    }
  }
  *this << ")" << beginb;
  return this;
}

JavaClassWriter* JavaClassWriter::BeginInnerClass(const JavaType& clazz,
    int modifiers) {
  *this << endl;
  JavaClassWriter* class_writer;
  if (modifiers & STATIC) {
    class_writer = new JavaClassWriter(src_writer_);
  } else {
    class_writer = new JavaClassWriter(src_writer_, declared_generics_);
  }
  return class_writer->Begin(clazz, modifiers);
}

JavaClassWriter* JavaWriter::BeginClass(const JavaType& clazz,
    const std::set<JavaType>& imports, int modifiers) {
  *this << "package " << clazz.package() << ";" << endl << endl;
  if (!imports.empty()) {
    std::set<JavaType>::const_iterator it;
    for (it = imports.cbegin(); it != imports.cend(); ++it) {
      if (!it->package().empty() && it->package() != clazz.package()) {
        *this << "import " << it->package() << "." << it->name() << ";" << endl;
      }
    }
    *this << endl;
  }
  JavaClassWriter* class_writer = new JavaClassWriter(src_writer_);
  return class_writer->Begin(clazz, modifiers);
}

}  // namespace java
}  // namespace tensorflow
