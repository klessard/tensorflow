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
package org.tensorflow.op.training;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class ResourceApplyFtrlV2 extends PrimitiveOp {
  
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
   * Factory method to create a class to wrap a new ResourceApplyFtrlV2 operation to the graph.
   * 
   * @param scope Current graph scope
   * @param var
   * @param accum
   * @param linear
   * @param grad
   * @param lr
   * @param l1
   * @param l2
   * @param l2Shrinkage
   * @param lrPower
   * @return a new instance of ResourceApplyFtrlV2
   **/
  public static <T> ResourceApplyFtrlV2 create(Scope scope, Operand<?> var, Operand<?> accum, Operand<?> linear, Operand<T> grad, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ResourceApplyFtrlV2", scope.makeOpName("ResourceApplyFtrlV2"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(linear.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(l2Shrinkage.asOutput());
    opBuilder.addInput(lrPower.asOutput());
    return new ResourceApplyFtrlV2(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new ResourceApplyFtrlV2 operation to the graph.
   * 
   * @param scope Current graph scope
   * @param var
   * @param accum
   * @param linear
   * @param grad
   * @param lr
   * @param l1
   * @param l2
   * @param l2Shrinkage
   * @param lrPower
   * @param options an object holding optional attributes values
   * @return a new instance of ResourceApplyFtrlV2
   **/
  public static <T> ResourceApplyFtrlV2 create(Scope scope, Operand<?> var, Operand<?> accum, Operand<?> linear, Operand<T> grad, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ResourceApplyFtrlV2", scope.makeOpName("ResourceApplyFtrlV2"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(linear.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(l2Shrinkage.asOutput());
    opBuilder.addInput(lrPower.asOutput());
    if (options.useLocking != null) {
      opBuilder.setAttr("useLocking", options.useLocking);
    }
    return new ResourceApplyFtrlV2(opBuilder.build());
  }
  
  /**
   * @param useLocking
   **/
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private ResourceApplyFtrlV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
  }
}
