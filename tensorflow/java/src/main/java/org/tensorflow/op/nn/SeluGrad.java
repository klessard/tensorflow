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
package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class SeluGrad<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class to wrap a new SeluGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param gradients
   * @param outputs
   * @return a new instance of SeluGrad
   **/
  public static <T> SeluGrad<T> create(Scope scope, Operand<T> gradients, Operand<T> outputs) {
    OperationBuilder opBuilder = scope.graph().opBuilder("SeluGrad", scope.makeOpName("SeluGrad"));
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(outputs.asOutput());
    return new SeluGrad<T>(opBuilder.build());
  }
  
  public Output<T> backprops() {
    return backprops;
  }
  
  @Override
  public Output<T> asOutput() {
    return backprops;
  }
  
  private Output<T> backprops;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private SeluGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }
}
