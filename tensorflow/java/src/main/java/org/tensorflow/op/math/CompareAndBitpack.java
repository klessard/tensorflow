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
package org.tensorflow.op.math;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.UInt8;

public final class CompareAndBitpack extends PrimitiveOp implements Operand<UInt8> {
  
  /**
   * Factory method to create a class to wrap a new CompareAndBitpack operation to the graph.
   * 
   * @param scope Current graph scope
   * @param input
   * @param threshold
   * @return a new instance of CompareAndBitpack
   **/
  public static <T> CompareAndBitpack create(Scope scope, Operand<T> input, Operand<T> threshold) {
    OperationBuilder opBuilder = scope.graph().opBuilder("CompareAndBitpack", scope.makeOpName("CompareAndBitpack"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(threshold.asOutput());
    return new CompareAndBitpack(opBuilder.build());
  }
  
  public Output<UInt8> output() {
    return output;
  }
  
  @Override
  public Output<UInt8> asOutput() {
    return output;
  }
  
  private Output<UInt8> output;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private CompareAndBitpack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
