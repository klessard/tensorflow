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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class SplitV<T> extends PrimitiveOp implements Iterable<Operand<T>> {
  
  /**
   * Factory method to create a class to wrap a new SplitV operation to the graph.
   * 
   * @param scope Current graph scope
   * @param value
   * @param sizeSplits
   * @param splitDim
   * @param numSplit
   * @return a new instance of SplitV
   **/
  public static <T, U> SplitV<T> create(Scope scope, Operand<T> value, Operand<U> sizeSplits, Operand<Integer> splitDim, Integer numSplit) {
    OperationBuilder opBuilder = scope.graph().opBuilder("SplitV", scope.makeOpName("SplitV"));
    opBuilder.addInput(value.asOutput());
    opBuilder.addInput(sizeSplits.asOutput());
    opBuilder.addInput(splitDim.asOutput());
    opBuilder.setAttr("numSplit", numSplit);
    return new SplitV<T>(opBuilder.build());
  }
  
  public List<Output<T>> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) output.iterator();
  }
  
  private List<Output<T>> output;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private SplitV(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[])operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }
}
