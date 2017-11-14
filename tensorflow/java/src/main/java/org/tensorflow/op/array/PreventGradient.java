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
package org.tensorflow.op.array;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class PreventGradient<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param message
     **/
    public Options message(String message) {
      this.message = message;
      return this;
    }
    
    private String message;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new PreventGradient operation to the graph.
   * 
   * @param scope Current graph scope
   * @param input
   * @return a new instance of PreventGradient
   **/
  public static <T> PreventGradient<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.graph().opBuilder("PreventGradient", scope.makeOpName("PreventGradient"));
    opBuilder.addInput(input.asOutput());
    return new PreventGradient<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new PreventGradient operation to the graph.
   * 
   * @param scope Current graph scope
   * @param input
   * @param options an object holding optional attributes values
   * @return a new instance of PreventGradient
   **/
  public static <T> PreventGradient<T> create(Scope scope, Operand<T> input, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("PreventGradient", scope.makeOpName("PreventGradient"));
    opBuilder.addInput(input.asOutput());
    if (options.message != null) {
      opBuilder.setAttr("message", options.message);
    }
    return new PreventGradient<T>(opBuilder.build());
  }
  
  /**
   * @param message
   **/
  public static Options message(String message) {
    return new Options().message(message);
  }
  
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private PreventGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
