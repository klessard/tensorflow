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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class Dilation2DBackpropInput<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class to wrap a new Dilation2DBackpropInput operation to the graph.
   * 
   * @param scope Current graph scope
   * @param input
   * @param filter
   * @param outBackprop
   * @param strides
   * @param rates
   * @param padding
   * @return a new instance of Dilation2DBackpropInput
   **/
  public static <T> Dilation2DBackpropInput<T> create(Scope scope, Operand<T> input, Operand<T> filter, Operand<T> outBackprop, List<Integer> strides, List<Integer> rates, String padding) {
    OperationBuilder opBuilder = scope.graph().opBuilder("Dilation2DBackpropInput", scope.makeOpName("Dilation2DBackpropInput"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    long[] ratesArray = new long[rates.size()];
    for (int i = 0; i < ratesArray.length; ++i) {
      ratesArray[i] = rates.get(i);
    }
    opBuilder.setAttr("rates", ratesArray);
    opBuilder.setAttr("padding", padding);
    return new Dilation2DBackpropInput<T>(opBuilder.build());
  }
  
  public Output<T> inBackprop() {
    return inBackprop;
  }
  
  @Override
  public Output<T> asOutput() {
    return inBackprop;
  }
  
  private Output<T> inBackprop;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private Dilation2DBackpropInput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    inBackprop = operation.output(outputIdx++);
  }
}
