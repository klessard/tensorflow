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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.Shape;
import org.tensorflow.op.Scope;

public final class ConditionalAccumulator extends PrimitiveOp implements Operand<String> {
  
  /**
   * Class holding optional attributes of this operation
   **/
  public static class Options {
    
    /**
     * @param container
     **/
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName
     **/
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    private String container;
    private String sharedName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class to wrap a new ConditionalAccumulator operation to the graph.
   * 
   * @param scope Current graph scope
   * @param dtype
   * @param shape
   * @return a new instance of ConditionalAccumulator
   **/
  public static <T> ConditionalAccumulator create(Scope scope, Class<T> dtype, Shape shape) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ConditionalAccumulator", scope.makeOpName("ConditionalAccumulator"));
    opBuilder.setAttr("dtype", DataType.fromClass(dtype));
    opBuilder.setAttr("shape", shape);
    return new ConditionalAccumulator(opBuilder.build());
  }
  
  /**
   * Factory method to create a class to wrap a new ConditionalAccumulator operation to the graph.
   * 
   * @param scope Current graph scope
   * @param dtype
   * @param shape
   * @param options an object holding optional attributes values
   * @return a new instance of ConditionalAccumulator
   **/
  public static <T> ConditionalAccumulator create(Scope scope, Class<T> dtype, Shape shape, Options options) {
    OperationBuilder opBuilder = scope.graph().opBuilder("ConditionalAccumulator", scope.makeOpName("ConditionalAccumulator"));
    opBuilder.setAttr("dtype", DataType.fromClass(dtype));
    opBuilder.setAttr("shape", shape);
    if (options.container != null) {
      opBuilder.setAttr("container", options.container);
    }
    if (options.sharedName != null) {
      opBuilder.setAttr("sharedName", options.sharedName);
    }
    return new ConditionalAccumulator(opBuilder.build());
  }
  
  /**
   * @param container
   **/
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName
   **/
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
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
  private ConditionalAccumulator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
