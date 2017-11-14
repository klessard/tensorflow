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
package org.tensorflow.op.summary;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.UInt8;

public final class WriteImageSummary extends PrimitiveOp {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param maxImages
     **/
    public Options maxImages(Integer maxImages) {
      this.maxImages = maxImages;
      return this;
    }
    
    private Integer maxImages;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new WriteImageSummary operation to the graph.
   * 
   * @param scope Current graph scope
   * @param writer
   * @param globalStep
   * @param tag
   * @param tensor
   * @param badColor
   * @return a new instance of WriteImageSummary
   **/
  public static <T> WriteImageSummary create(Scope scope, Operand<?> writer, Operand<Long> globalStep, Operand<String> tag, Operand<T> tensor, Operand<UInt8> badColor) {
    OperationBuilder opBuilder = scope.graph().opBuilder("WriteImageSummary", scope.makeOpName("WriteImageSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(globalStep.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(badColor.asOutput());
    return new WriteImageSummary(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new WriteImageSummary operation to the graph.
   * 
   * @param scope Current graph scope
   * @param writer
   * @param globalStep
   * @param tag
   * @param tensor
   * @param badColor
   * @param options an object holding optional attributes values
   * @return a new instance of WriteImageSummary
   **/
  public static <T> WriteImageSummary create(Scope scope, Operand<?> writer, Operand<Long> globalStep, Operand<String> tag, Operand<T> tensor, Operand<UInt8> badColor, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("WriteImageSummary", scope.makeOpName("WriteImageSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(globalStep.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(badColor.asOutput());
    if (options.maxImages != null) {
      opBuilder.setAttr("maxImages", options.maxImages);
    }
    return new WriteImageSummary(opBuilder.build());
  }
  
  /**
   * @param maxImages
   **/
  public static Options maxImages(Integer maxImages) {
    return new Options().maxImages(maxImages);
  }
  
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private WriteImageSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
  }
}
