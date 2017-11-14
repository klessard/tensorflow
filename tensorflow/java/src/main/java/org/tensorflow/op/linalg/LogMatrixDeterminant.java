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
package org.tensorflow.op.linalg;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class LogMatrixDeterminant<T> extends PrimitiveOp {
  
  /**
   * Factory method to create a class to wrap a new LogMatrixDeterminant operation to the graph.
   * 
   * @param scope Current graph scope
   * @param input
   * @return a new instance of LogMatrixDeterminant
   **/
  public static <T> LogMatrixDeterminant<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.graph().opBuilder("LogMatrixDeterminant", scope.makeOpName("LogMatrixDeterminant"));
    opBuilder.addInput(input.asOutput());
    return new LogMatrixDeterminant<T>(opBuilder.build());
  }
  
  public Output<T> sign() {
    return sign;
  }
  
  public Output<T> logAbsDeterminant() {
    return logAbsDeterminant;
  }
  
  private Output<T> sign;
  private Output<T> logAbsDeterminant;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private LogMatrixDeterminant(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sign = operation.output(outputIdx++);
    logAbsDeterminant = operation.output(outputIdx++);
  }
}
