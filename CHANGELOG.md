# Changelog

## 📦 Version [v0.3.3](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.3.3)

### 📚 Data Sources

 * ```[PharmGKB]``` Updated data source module for new download files and data schema changes
 * ```[PharmGKB]``` Literature nodes are now mapped as publication

### 🔧 Bug fixes and other changes

 * ```[feature]``` Edge path mappings now allow directional information to prevent redundantly mapped relationships
 * ```[feature]``` Class mapping annotations from superclasses are now used in node models

---

## 📦 Version [v0.3.2](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.3.2)

### 🔧 Bug fixes and other changes

 * ```[fix]``` Fixed a sanity error blocking addition of new data sources for newly created workspaces

---

## 📦 Version [v0.3.1](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.3.1)

### 📚 Data Sources

 * ```[USDA-PLANTS]``` Update download URL for new USDA PLANTS website
 * ```[DrugCentral]``` Map structures as both drug and compound
 * ```[DrugCentral]``` Map drug interaction, indication, and contraindication paths

### 🔧 Bug fixes and other changes

 * ```[feature]``` GraphExporter now has an export version to trigger a reexport if a data source exporter changed
 * ```[feature]``` Mapped edges are now added between all mapping nodes of source and target node
 * ```[mapping]``` Node type SIDE_EFFECT is renamed to ADVERSE_EVENT
 * ```[docs]``` Add simple analysis example documentation for DrugCentral and CancerDrugsDB

---

## 📦 Version [v0.3.0](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.3.0)

### 📚 Data Sources

 * ```[CancerDrugsDB]``` Finished new CancerDrugsDB data source module
 * ```[ITIS]``` Finish graph exporter
 * ```[HPO]``` Implemented HPO updater
 * ```[ABDAMED2]``` Implemented K2 file format reader
 * ```[DrugBank]``` Remove redundant nodes and match article dois for mapping

### 🔧 Bug fixes and other changes

 * ```[feature]``` Meta graph generation and GraphML export can now be skipped in the config.json
 * ```[feature]``` Add generation and packaging of maven dependency attribution xml
 * ```[feature]``` Add CodeQL analysis GitHub action
 * ```[feature]``` Graphs can now be opened as readonly as done for merging
 * ```[fix]``` Not all HTTPClient methods followed redirects
 * ```[fix]``` Meta graph generation now skips for empty graphs

---

## 📦 Version [v0.2.1](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.2.1)

### 🔧 Bug fixes and other changes

 * ```[feature]``` Implemented GFF3 file format reader
 * ```[feature]``` Add subclasses for specific OBO file format entries
 * ```[feature]``` Improve graph MVStoreIndex performance

---

## 📦 Version [v0.2.0](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.2.0)

---

## 📦 Version [v0.1.8](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.8)

---

## 📦 Version [v0.1.7](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.7)

---

## 📦 Version [v0.1.6](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.6)

---

## 📦 Version [v0.1.5](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.5)

---

## 📦 Version [v0.1.4](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.4)

---

## 📦 Version [v0.1.3](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.3)

---

## 📦 Version [v0.1.2](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.2)

---

## 📦 Version [v0.1.1](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1.1)

---

## 📦 Version [v0.1](https://github.com/BioDWH2/BioDWH2/releases/tag/v0.1)
 