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
package org.tensorflow.op.dataset;

import org.tensorflow.DataType;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.Shape;
import org.tensorflow.op.Scope;

public final class Iterator extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class to wrap a new Iterator operation to the graph.
   * 
   * @param scope Current graph scope
   * @param sharedName
   * @param container
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of Iterator
   **/
  public static Iterator create(Scope scope, String sharedName, String container, List<DataType> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.graph().opBuilder("Iterator", scope.makeOpName("Iterator"));
    opBuilder.setAttr("sharedName", sharedName);
    opBuilder.setAttr("container", container);
    opBuilder.setAttr("outputTypes", outputTypes.toArray(new DataType[outputTypes.size()]));
    opBuilder.setAttr("outputShapes", outputShapes.toArray(new Shape[outputShapes.size()]));
    return new Iterator(opBuilder.build());
  }
  
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private Iterator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
