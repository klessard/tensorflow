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
package org.tensorflow.op.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class SparseSparseMinimum<T> extends PrimitiveOp {
  
  /**
   * Factory method to create a class to wrap a new SparseSparseMinimum operation to the graph.
   * 
   * @param scope Current graph scope
   * @param aIndices
   * @param aValues
   * @param aShape
   * @param bIndices
   * @param bValues
   * @param bShape
   * @return a new instance of SparseSparseMinimum
   **/
  public static <T> SparseSparseMinimum<T> create(Scope scope, Operand<Long> aIndices, Operand<T> aValues, Operand<Long> aShape, Operand<Long> bIndices, Operand<T> bValues, Operand<Long> bShape) {
    OperationBuilder opBuilder = scope.graph().opBuilder("SparseSparseMinimum", scope.makeOpName("SparseSparseMinimum"));
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(bValues.asOutput());
    opBuilder.addInput(bShape.asOutput());
    return new SparseSparseMinimum<T>(opBuilder.build());
  }
  
  public Output<Long> outputIndices() {
    return outputIndices;
  }
  
  public Output<T> outputValues() {
    return outputValues;
  }
  
  private Output<Long> outputIndices;
  private Output<T> outputValues;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private SparseSparseMinimum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
  }
}
