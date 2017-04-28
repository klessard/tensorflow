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

#include "generator.h"
#include "tensorflow/core/lib/core/stringpiece.h"
#include "tensorflow/core/platform/init_main.h"
#include "tensorflow/core/platform/env.h"


int main(int argc, char* argv[]) {
  tensorflow::port::InitMain(argv[0], &argc, &argv);
  if (argc != 3) {
    fprintf(stderr,
            "Usage: %s out_file include_internal\n"
            "  include_internal: 1 means include internal ops\n",
            argv[0]);
    exit(1);
  }
  bool include_internal = tensorflow::StringPiece("1") == argv[2];
  tensorflow::OpGenerator generator(argv[1]);
  generator.Run(tensorflow::Env::Default(), include_internal);
  return 0;
}
