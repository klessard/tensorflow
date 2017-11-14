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
package org.tensorflow.op.image;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

public final class ResizeBilinearGrad<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param alignCorners
     **/
    public Options alignCorners(Boolean alignCorners) {
      this.alignCorners = alignCorners;
      return this;
    }
    
    private Boolean alignCorners;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new ResizeBilinearGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param grads
   * @param originalImage
   * @return a new instance of ResizeBilinearGrad
   **/
  public static <T> ResizeBilinearGrad<T> create(Scope scope, Operand<Float> grads, Operand<T> originalImage) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ResizeBilinearGrad", scope.makeOpName("ResizeBilinearGrad"));
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(originalImage.asOutput());
    return new ResizeBilinearGrad<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new ResizeBilinearGrad operation to the graph.
   * 
   * @param scope Current graph scope
   * @param grads
   * @param originalImage
   * @param options an object holding optional attributes values
   * @return a new instance of ResizeBilinearGrad
   **/
  public static <T> ResizeBilinearGrad<T> create(Scope scope, Operand<Float> grads, Operand<T> originalImage, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ResizeBilinearGrad", scope.makeOpName("ResizeBilinearGrad"));
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(originalImage.asOutput());
    if (options.alignCorners != null) {
      opBuilder.setAttr("alignCorners", options.alignCorners);
    }
    return new ResizeBilinearGrad<T>(opBuilder.build());
  }
  
  /**
   * @param alignCorners
   **/
  public static Options alignCorners(Boolean alignCorners) {
    return new Options().alignCorners(alignCorners);
  }
  
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  /**
   * @param operation
   **/
  @SuppressWarnings("unchecked")
  private ResizeBilinearGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
