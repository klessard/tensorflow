/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow;

/**
 * A reference to a list of variable tensor.
 *
 * <p>The principal goal of this interface is to enforce type strictness for operation that takes in
 * input a variable tensor. It is allowed to pass a variable tensor as a any other input tensor but
 * not the opposite.
 *
 * <pre>{@code
 * VariableRef var = ....;
 * ops.math.mean(var, ...); // will work
 * ops.training.applyGradientDescent(var, ...); // will work
 *
 * Input input = ...;
 * ops.math.mean(input, ...); // will work
 * ops.training.applyGradientDescent(input, ...); // won't work
 * }</pre>
 */
public interface VariableRefList extends InputList {

  /** Return the list of variable tensor as a {@link OutputList} */
  OutputList asOutputList();
}
