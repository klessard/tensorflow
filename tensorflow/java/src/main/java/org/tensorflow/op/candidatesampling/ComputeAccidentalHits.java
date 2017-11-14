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
package org.tensorflow.op.candidatesampling;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class ComputeAccidentalHits extends PrimitiveOp {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param seed
     **/
    public Options seed(Integer seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2
     **/
    public Options seed2(Integer seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    private Integer seed;
    private Integer seed2;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new ComputeAccidentalHits operation to the graph.
   * 
   * @param scope Current graph scope
   * @param trueClasses
   * @param sampledCandidates
   * @param numTrue
   * @return a new instance of ComputeAccidentalHits
   **/
  public static ComputeAccidentalHits create(Scope scope, Operand<Long> trueClasses, Operand<Long> sampledCandidates, Integer numTrue) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ComputeAccidentalHits", scope.makeOpName("ComputeAccidentalHits"));
    opBuilder.addInput(trueClasses.asOutput());
    opBuilder.addInput(sampledCandidates.asOutput());
    opBuilder.setAttr("numTrue", numTrue);
    return new ComputeAccidentalHits(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new ComputeAccidentalHits operation to the graph.
   * 
   * @param scope Current graph scope
   * @param trueClasses
   * @param sampledCandidates
   * @param numTrue
   * @param options an object holding optional attributes values
   * @return a new instance of ComputeAccidentalHits
   **/
  public static ComputeAccidentalHits create(Scope scope, Operand<Long> trueClasses, Operand<Long> sampledCandidates, Integer numTrue, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ComputeAccidentalHits", scope.makeOpName("ComputeAccidentalHits"));
    opBuilder.addInput(trueClasses.asOutput());
    opBuilder.addInput(sampledCandidates.asOutput());
    opBuilder.setAttr("numTrue", numTrue);
    if (options.seed != null) {
      opBuilder.setAttr("seed", options.seed);
    }
    if (options.seed2 != null) {
      opBuilder.setAttr("seed2", options.seed2);
    }
    return new ComputeAccidentalHits(opBuilder.build());
  }
  
  /**
   * @param seed
   **/
  public static Options seed(Integer seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2
   **/
  public static Options seed2(Integer seed2) {
    return new Options().seed2(seed2);
  }
  
  public Output<Integer> indices() {
    return indices;
  }
  
  public Output<Long> ids() {
    return ids;
  }
  
  public Output<Float> weights() {
    return weights;
  }
  
  private Output<Integer> indices;
  private Output<Long> ids;
  private Output<Float> weights;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private ComputeAccidentalHits(Operation operation) {
    super(operation);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    ids = operation.output(outputIdx++);
    weights = operation.output(outputIdx++);
  }
}
