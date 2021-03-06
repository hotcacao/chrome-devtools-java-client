package com.github.kklisura.cdt.protocol.types.animation;

/*-
 * #%L
 * cdt-java-client
 * %%
 * Copyright (C) 2018 Kenan Klisura
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.kklisura.cdt.protocol.support.annotations.Experimental;
import com.github.kklisura.cdt.protocol.support.annotations.Optional;

/** AnimationEffect instance */
@Experimental
public class AnimationEffect {

  private Double delay;

  private Double endDelay;

  private Double iterationStart;

  private Double iterations;

  private Double duration;

  private String direction;

  private String fill;

  private Integer backendNodeId;

  @Optional private KeyframesRule keyframesRule;

  private String easing;

  /** <code>AnimationEffect</code>'s delay. */
  public Double getDelay() {
    return delay;
  }

  /** <code>AnimationEffect</code>'s delay. */
  public void setDelay(Double delay) {
    this.delay = delay;
  }

  /** <code>AnimationEffect</code>'s end delay. */
  public Double getEndDelay() {
    return endDelay;
  }

  /** <code>AnimationEffect</code>'s end delay. */
  public void setEndDelay(Double endDelay) {
    this.endDelay = endDelay;
  }

  /** <code>AnimationEffect</code>'s iteration start. */
  public Double getIterationStart() {
    return iterationStart;
  }

  /** <code>AnimationEffect</code>'s iteration start. */
  public void setIterationStart(Double iterationStart) {
    this.iterationStart = iterationStart;
  }

  /** <code>AnimationEffect</code>'s iterations. */
  public Double getIterations() {
    return iterations;
  }

  /** <code>AnimationEffect</code>'s iterations. */
  public void setIterations(Double iterations) {
    this.iterations = iterations;
  }

  /** <code>AnimationEffect</code>'s iteration duration. */
  public Double getDuration() {
    return duration;
  }

  /** <code>AnimationEffect</code>'s iteration duration. */
  public void setDuration(Double duration) {
    this.duration = duration;
  }

  /** <code>AnimationEffect</code>'s playback direction. */
  public String getDirection() {
    return direction;
  }

  /** <code>AnimationEffect</code>'s playback direction. */
  public void setDirection(String direction) {
    this.direction = direction;
  }

  /** <code>AnimationEffect</code>'s fill mode. */
  public String getFill() {
    return fill;
  }

  /** <code>AnimationEffect</code>'s fill mode. */
  public void setFill(String fill) {
    this.fill = fill;
  }

  /** <code>AnimationEffect</code>'s target node. */
  public Integer getBackendNodeId() {
    return backendNodeId;
  }

  /** <code>AnimationEffect</code>'s target node. */
  public void setBackendNodeId(Integer backendNodeId) {
    this.backendNodeId = backendNodeId;
  }

  /** <code>AnimationEffect</code>'s keyframes. */
  public KeyframesRule getKeyframesRule() {
    return keyframesRule;
  }

  /** <code>AnimationEffect</code>'s keyframes. */
  public void setKeyframesRule(KeyframesRule keyframesRule) {
    this.keyframesRule = keyframesRule;
  }

  /** <code>AnimationEffect</code>'s timing function. */
  public String getEasing() {
    return easing;
  }

  /** <code>AnimationEffect</code>'s timing function. */
  public void setEasing(String easing) {
    this.easing = easing;
  }
}
