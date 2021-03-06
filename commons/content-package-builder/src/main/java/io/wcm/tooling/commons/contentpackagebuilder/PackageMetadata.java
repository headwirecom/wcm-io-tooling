/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2015 wcm.io
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
package io.wcm.tooling.commons.contentpackagebuilder;

import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_AC_HANDLING;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_CREATED;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_CREATED_BY;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_DESCRIPTION;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_GROUP;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_NAME;
import static org.apache.jackrabbit.vault.packaging.PackageProperties.NAME_VERSION;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.jackrabbit.util.ISO8601;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Package metadata
 */
final class PackageMetadata {

  private String group;
  private String name;
  private String description;
  private String createdBy = "admin";
  private Date created = new Date();
  private String version = "1.0";
  private AcHandling acHandling;
  private List<PackageFilter> filters = new ArrayList<>();
  private Map<String, String> xmlNamespaces = new HashMap<>();

  /**
   * Default constructor
   */
  PackageMetadata() {
    // register default XML namesapces
    xmlNamespaces.putAll(XmlNamespaces.DEFAULT_NAMESPACES);
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setAcHandling(AcHandling acHandling) {
    this.acHandling = acHandling;
  }

  public void addFilter(PackageFilter filter) {
    filters.add(filter);
  }

  public List<PackageFilter> getFilters() {
    return ImmutableList.copyOf(filters);
  }

  public void addXmlNamespace(String prefix, String uri) {
    xmlNamespaces.put(prefix, uri);
  }

  /**
   * @return XML namespaces
   */
  public Map<String, String> getXmlNamespaces() {
    return ImmutableMap.copyOf(this.xmlNamespaces);
  }

  /**
   * Validates that the mandatory properties are set.
   */
  public void validate() {
    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(group)) {
      throw new IllegalArgumentException("Package name or group not set.");
    }
    if (filters.isEmpty()) {
      throw new IllegalArgumentException("No package filter defined / no package root path set.");
    }
    if (created == null) {
      throw new IllegalArgumentException("Package creation date not set.");
    }
  }

  /**
   * @return Variables for placeholder replacement in package metadata.
   */
  public Map<String, Object> getVars() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(created);
    return ImmutableMap.<String,Object>builder()
        .put(NAME_GROUP, StringUtils.defaultString(group))
        .put(NAME_NAME, StringUtils.defaultString(name))
        .put(NAME_DESCRIPTION, StringUtils.defaultString(description))
        .put(NAME_CREATED, ISO8601.format(calendar))
        .put(NAME_CREATED_BY, StringUtils.defaultString(createdBy))
        .put(NAME_VERSION, StringUtils.defaultString(version))
        .put(NAME_AC_HANDLING, acHandling != null ? acHandling.getMode() : "")
        .put("path", "/etc/packages/" + group + "/" + name + ".zip")
        .build();
  }

}
