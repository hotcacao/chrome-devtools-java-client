package com.github.kklisura.cdt.protocol.types.accessibility;

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

import com.fasterxml.jackson.annotation.JsonProperty;

/** Relationships between elements other than parent/child/sibling. */
public enum AXRelationshipAttributes {
  @JsonProperty("activedescendant")
  ACTIVEDESCENDANT,
  @JsonProperty("controls")
  CONTROLS,
  @JsonProperty("describedby")
  DESCRIBEDBY,
  @JsonProperty("details")
  DETAILS,
  @JsonProperty("errormessage")
  ERRORMESSAGE,
  @JsonProperty("flowto")
  FLOWTO,
  @JsonProperty("labelledby")
  LABELLEDBY,
  @JsonProperty("owns")
  OWNS
}
