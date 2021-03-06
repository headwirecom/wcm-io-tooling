## AEM Global Parent

Global parent for Maven artifact hierarchy for AEM projects. Defines AEM-specific plugins and general Maven settings.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.wcm.maven/io.wcm.maven.aem-global-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.wcm.maven/io.wcm.maven.aem-global-parent)


### Overview

The settings in this AEM global parent POM cover:

* Dependencies for SCR and OSGi annotations
* Resource includes for src/main/webapp folder
* Default configurations for maven-bundle-plugin, maven-sling-plugin, content-package-maven-plugin
* Include bnd Plugins for 
  [Sling Models](https://sling.apache.org/documentation/bundles/models.html)
  and [Sling Context-Aware Configuration](https://sling.apache.org/documentation/bundles/context-aware-configuration/context-aware-configuration.html).
* Include wcm.io maven plugins [wcmio-content-package-maven-plugin](plugins/wcmio-content-package-maven-plugin/),
  [cq-maven-plugin](plugins/cq-maven-plugin/),
  [i18n-maven-plugin](plugins/i18n-maven-plugin/),
  [nodejs-maven-plugin](plugins/nodejs-maven-plugin/),
  [conga-maven-plugin](http://devops.wcm.io/conga/),
  [conga-aem-maven-plugin](http://devops.wcm.io/conga/plugins/aem/)
* Define default exclusions for m2e lifecycle
