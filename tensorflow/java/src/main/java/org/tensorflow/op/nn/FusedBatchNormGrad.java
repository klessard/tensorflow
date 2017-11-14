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

public final class FusedBatchNormGrad<T> extends PrimitiveOp {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param epsilon
     **/
    public Options epsilon(Float epsilon) {
      this.epsilon = epsilon;
      return this;
    }
    
    /**
     * @param dataFormat
     **/
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
    
    /**
     * @param isTraining
     **/
    public Options isTraining(Boolean isTraining) {
      this.isTraining = isTraining;
      return this;
    }
    
    private Float epsilon;
    private String dataFormat;
    private Boolean isTraining;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new FusedBatchNormGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param yBackprop
   * @param x
   * @param scale
   * @param reserveSpace1
   * @param reserveSpace2
   * @return a new instance of FusedBatchNormGrad
   **/
  public static <T> FusedBatchNormGrad<T> create(Scope scope, Operand<T> yBackprop, Operand<T> x, Operand<T> scale, Operand<T> reserveSpace1, Operand<T> reserveSpace2) {
    OperationBuilder opBuilder = scope.graph().opBuilder("FusedBatchNormGrad", scope.makeOpName("FusedBatchNormGrad"));
    opBuilder.addInput(yBackprop.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(reserveSpace1.asOutput());
    opBuilder.addInput(reserveSpace2.asOutput());
    return new FusedBatchNormGrad<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new FusedBatchNormGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param yBackprop
   * @param x
   * @param scale
   * @param reserveSpace1
   * @param reserveSpace2
   * @param options an object holding optional attributes values
   * @return a new instance of FusedBatchNormGrad
   **/
  public static <T> FusedBatchNormGrad<T> create(Scope scope, Operand<T> yBackprop, Operand<T> x, Operand<T> scale, Operand<T> reserveSpace1, Operand<T> reserveSpace2, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("FusedBatchNormGrad", scope.makeOpName("FusedBatchNormGrad"));
    opBuilder.addInput(yBackprop.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(reserveSpace1.asOutput());
    opBuilder.addInput(reserveSpace2.asOutput());
    if (options.epsilon != null) {
      opBuilder.setAttr("epsilon", options.epsilon);
    }
    if (options.dataFormat != null) {
      opBuilder.setAttr("dataFormat", options.dataFormat);
    }
    if (options.isTraining != null) {
      opBuilder.setAttr("isTraining", options.isTraining);
    }
    return new FusedBatchNormGrad<T>(opBuilder.build());
  }
  
  /**
   * @param epsilon
   **/
  public static Options epsilon(Float epsilon) {
    return new Options().epsilon(epsilon);
  }
  
  /**
   * @param dataFormat
   **/
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }
  
  /**
   * @param isTraining
   **/
  public static Options isTraining(Boolean isTraining) {
    return new Options().isTraining(isTraining);
  }
  
  public Output<T> xBackprop() {
    return xBackprop;
  }
  
  public Output<T> scaleBackprop() {
    return scaleBackprop;
  }
  
  public Output<T> offsetBackprop() {
    return offsetBackprop;
  }
  
  public Output<T> reserveSpace3() {
    return reserveSpace3;
  }
  
  public Output<T> reserveSpace4() {
    return reserveSpace4;
  }
  
  private Output<T> xBackprop;
  private Output<T> scaleBackprop;
  private Output<T> offsetBackprop;
  private Output<T> reserveSpace3;
  private Output<T> reserveSpace4;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private FusedBatchNormGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    xBackprop = operation.output(outputIdx++);
    scaleBackprop = operation.output(outputIdx++);
    offsetBackprop = operation.output(outputIdx++);
    reserveSpace3 = operation.output(outputIdx++);
    reserveSpace4 = operation.output(outputIdx++);
  }
}
