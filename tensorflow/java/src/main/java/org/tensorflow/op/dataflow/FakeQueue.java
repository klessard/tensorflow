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
package org.tensorflow.op.dataflow;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class FakeQueue extends PrimitiveOp implements Operand<String> {
  
  /**
   * Factory method to create a class to wrap a new FakeQueue operation to the graph.
   * 
   * @param scope Current graph scope
   * @param resource
   * @return a new instance of FakeQueue
   **/
  public static FakeQueue create(Scope scope, Operand<?> resource) {
    OperationBuilder opBuilder = scope.graph().opBuilder("FakeQueue", scope.makeOpName("FakeQueue"));
    opBuilder.addInput(resource.asOutput());
    return new FakeQueue(opBuilder.build());
  }
  
  public Output<String> handle() {
    return handle;
  }
  
  @Override
  public Output<String> asOutput() {
    return handle;
  }
  
  private Output<String> handle;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private FakeQueue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
