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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class QueueCloseV2 extends PrimitiveOp {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param cancelPendingEnqueues
     **/
    public Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
      this.cancelPendingEnqueues = cancelPendingEnqueues;
      return this;
    }
    
    private Boolean cancelPendingEnqueues;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new QueueCloseV2 operation to the graph.
   * 
   * @param scope Current graph scope
   * @param handle
   * @return a new instance of QueueCloseV2
   **/
  public static QueueCloseV2 create(Scope scope, Operand<?> handle) {
    OperationBuilder opBuilder = scope.graph().opBuilder("QueueCloseV2", scope.makeOpName("QueueCloseV2"));
    opBuilder.addInput(handle.asOutput());
    return new QueueCloseV2(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new QueueCloseV2 operation to the graph.
   * 
   * @param scope Current graph scope
   * @param handle
   * @param options an object holding optional attributes values
   * @return a new instance of QueueCloseV2
   **/
  public static QueueCloseV2 create(Scope scope, Operand<?> handle, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("QueueCloseV2", scope.makeOpName("QueueCloseV2"));
    opBuilder.addInput(handle.asOutput());
    if (options.cancelPendingEnqueues != null) {
      opBuilder.setAttr("cancelPendingEnqueues", options.cancelPendingEnqueues);
    }
    return new QueueCloseV2(opBuilder.build());
  }
  
  /**
   * @param cancelPendingEnqueues
   **/
  public static Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
    return new Options().cancelPendingEnqueues(cancelPendingEnqueues);
  }
  
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private QueueCloseV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
  }
}
