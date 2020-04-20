/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.extensions.ml;

import com.google.cloud.videointelligence.v1.Feature;
import com.google.cloud.videointelligence.v1.VideoAnnotationResults;
import com.google.cloud.videointelligence.v1.VideoContext;
import java.util.List;
import java.util.Map;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;

/**
 * Base class for VideoIntelligence PTransform.
 *
 * @param <T> Type of input PCollection contents.
 */
@Experimental
public abstract class AnnotateVideo<T>
    extends PTransform<PCollection<T>, PCollection<List<VideoAnnotationResults>>> {
  protected final PCollectionView<Map<T, VideoContext>> contextSideInput;
  protected final List<Feature> featureList;

  protected AnnotateVideo(
      PCollectionView<Map<T, VideoContext>> contextSideInput, List<Feature> featureList) {
    this.contextSideInput = contextSideInput;
    this.featureList = featureList;
  }

  protected AnnotateVideo(List<Feature> featureList) {
    this.contextSideInput = null;
    this.featureList = featureList;
  }

  /**
   * To be implemented based on input PCollection's content type.
   *
   * @param input
   * @return
   */
  @Override
  public abstract PCollection<List<VideoAnnotationResults>> expand(PCollection<T> input);
}
