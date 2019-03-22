/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Implementation for an {@link Operation} executed eagerly.
 */
class EagerOperation extends AbstractOperation {
  
  EagerOperation(EagerSession session, long nativeHandle, long[] outputNativeHandles, String type, String name) {
    this.nativeRef = new NativeReference(session, this, nativeHandle, outputNativeHandles);
    this.outputTensors = new AtomicReferenceArray<Tensor<?>>(outputNativeHandles.length);
    this.type = type;
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String type() {
    return type;
  }

  @Override
  public int numOutputs() {
    return nativeRef.tensorHandles.length;
  }

  @Override
  public int outputListLength(final String name) {
    return outputListLength(nativeRef.opHandle, name);
  }

  @Override
  public int inputListLength(final String name) {
    return inputListLength(nativeRef.opHandle, name);
  }

  @Override
  public long getUnsafeNativeHandle(int outputIndex) {
    return nativeRef.tensorHandles[outputIndex];
  }

  @Override
  public long[] shape(int outputIndex) {
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor != null) {
      return tensor.shape();
    }
    long outputNativeHandle = getUnsafeNativeHandle(outputIndex);
    long[] shape = new long[numDims(outputNativeHandle)];
    for (int i = 0; i < shape.length; ++i) {
      shape[i] = dim(outputNativeHandle, i);
    }
    return shape;
  }

  @Override
  public DataType dtype(int outputIndex) {
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor != null) {
      return tensor.dataType();
    }
    return DataType.fromC(dataType(getUnsafeNativeHandle(outputIndex)));
  }
  
  @Override
  public Tensor<?> tensor(int outputIndex) {
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor == null) {
      // Take an optimistic approach, where we attempt to resolve the output tensor without locking.
      // If another thread resolved it meanwhile, release our copy and use the existing one.
      tensor = Tensor.fromHandle(resolveTensorHandle(getUnsafeNativeHandle(outputIndex)));
      if (!outputTensors.compareAndSet(outputIndex, null, tensor)) {
        tensor.close();
        tensor = outputTensors.get(outputIndex);
      }
    }
    return tensor;
  }

  private static class NativeReference extends EagerSession.NativeReference {

    NativeReference(EagerSession session, EagerOperation operation, long opHandle, long[] tensorHandles) {
      super(session, operation);
      this.opHandle = opHandle;
      this.tensorHandles = tensorHandles;
    }

    @Override
    void delete() {
      for (long tensorHandle : tensorHandles) {
        EagerOperation.deleteTensorHandle(tensorHandle);
      }
      EagerOperation.delete(opHandle);
    }
    
    private final long opHandle;
    private final long[] tensorHandles;
  }

  private final NativeReference nativeRef;
  private final AtomicReferenceArray<Tensor<?>> outputTensors;  // only tensors that has been accessed so far are non-null
  private final String type;
  private final String name;
  
  private static native void delete(long handle);

  private static native void deleteTensorHandle(long handle);

  private static native long resolveTensorHandle(long handle);
  
  private static native int outputListLength(long handle, String name);

  private static native int inputListLength(long handle, String name);

  private static native int dataType(long handle);

  private static native int numDims(long handle);

  private static native long dim(long handle, int index);
}
