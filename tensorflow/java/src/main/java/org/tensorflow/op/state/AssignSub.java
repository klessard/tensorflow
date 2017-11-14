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
package org.tensorflow.op.state;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class AssignSub<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param useLocking
     **/
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
    
    private Boolean useLocking;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new AssignSub operation to the graph.
   * 
   * @param scope Current graph scope
   * @param ref
   * @param value
   * @return a new instance of AssignSub
   **/
  public static <T> AssignSub<T> create(Scope scope, Operand<T> ref, Operand<T> value) {
    OperationBuilder opBuilder = scope.graph().opBuilder("AssignSub", scope.makeOpName("AssignSub"));
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(value.asOutput());
    return new AssignSub<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new AssignSub operation to the graph.
   * 
   * @param scope Current graph scope
   * @param ref
   * @param value
   * @param options an object holding optional attributes values
   * @return a new instance of AssignSub
   **/
  public static <T> AssignSub<T> create(Scope scope, Operand<T> ref, Operand<T> value, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("AssignSub", scope.makeOpName("AssignSub"));
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(value.asOutput());
    if (options.useLocking != null) {
      opBuilder.setAttr("useLocking", options.useLocking);
    }
    return new AssignSub<T>(opBuilder.build());
  }
  
  /**
   * @param useLocking
   **/
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  public Output<T> outputRef() {
    return outputRef;
  }
  
  @Override
  public Output<T> asOutput() {
    return outputRef;
  }
  
  private Output<T> outputRef;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private AssignSub(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputRef = operation.output(outputIdx++);
  }
}
