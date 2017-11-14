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

public final class StridedSliceGrad<U> extends PrimitiveOp implements Operand<U> {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param beginMask
     **/
    public Options beginMask(Integer beginMask) {
      this.beginMask = beginMask;
      return this;
    }
    
    /**
     * @param endMask
     **/
    public Options endMask(Integer endMask) {
      this.endMask = endMask;
      return this;
    }
    
    /**
     * @param ellipsisMask
     **/
    public Options ellipsisMask(Integer ellipsisMask) {
      this.ellipsisMask = ellipsisMask;
      return this;
    }
    
    /**
     * @param newAxisMask
     **/
    public Options newAxisMask(Integer newAxisMask) {
      this.newAxisMask = newAxisMask;
      return this;
    }
    
    /**
     * @param shrinkAxisMask
     **/
    public Options shrinkAxisMask(Integer shrinkAxisMask) {
      this.shrinkAxisMask = shrinkAxisMask;
      return this;
    }
    
    private Integer beginMask;
    private Integer endMask;
    private Integer ellipsisMask;
    private Integer newAxisMask;
    private Integer shrinkAxisMask;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new StridedSliceGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param shape
   * @param begin
   * @param end
   * @param strides
   * @param dy
   * @return a new instance of StridedSliceGrad
   **/
  public static <T, U> StridedSliceGrad<U> create(Scope scope, Operand<T> shape, Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy) {
    OperationBuilder opBuilder = scope.graph().opBuilder("StridedSliceGrad", scope.makeOpName("StridedSliceGrad"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.addInput(dy.asOutput());
    return new StridedSliceGrad<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new StridedSliceGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param shape
   * @param begin
   * @param end
   * @param strides
   * @param dy
   * @param options an object holding optional attributes values
   * @return a new instance of StridedSliceGrad
   **/
  public static <T, U> StridedSliceGrad<U> create(Scope scope, Operand<T> shape, Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("StridedSliceGrad", scope.makeOpName("StridedSliceGrad"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.addInput(dy.asOutput());
    if (options.beginMask != null) {
      opBuilder.setAttr("beginMask", options.beginMask);
    }
    if (options.endMask != null) {
      opBuilder.setAttr("endMask", options.endMask);
    }
    if (options.ellipsisMask != null) {
      opBuilder.setAttr("ellipsisMask", options.ellipsisMask);
    }
    if (options.newAxisMask != null) {
      opBuilder.setAttr("newAxisMask", options.newAxisMask);
    }
    if (options.shrinkAxisMask != null) {
      opBuilder.setAttr("shrinkAxisMask", options.shrinkAxisMask);
    }
    return new StridedSliceGrad<U>(opBuilder.build());
  }
  
  /**
   * @param beginMask
   **/
  public static Options beginMask(Integer beginMask) {
    return new Options().beginMask(beginMask);
  }
  
  /**
   * @param endMask
   **/
  public static Options endMask(Integer endMask) {
    return new Options().endMask(endMask);
  }
  
  /**
   * @param ellipsisMask
   **/
  public static Options ellipsisMask(Integer ellipsisMask) {
    return new Options().ellipsisMask(ellipsisMask);
  }
  
  /**
   * @param newAxisMask
   **/
  public static Options newAxisMask(Integer newAxisMask) {
    return new Options().newAxisMask(newAxisMask);
  }
  
  /**
   * @param shrinkAxisMask
   **/
  public static Options shrinkAxisMask(Integer shrinkAxisMask) {
    return new Options().shrinkAxisMask(shrinkAxisMask);
  }
  
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  private Output<U> output;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private StridedSliceGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
