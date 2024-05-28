package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubcellularLocation {
        @JsonProperty("Gene")
        public String gene;
        @JsonProperty("Gene name")
        public String geneName;
        @JsonProperty("Reliability")
        public String reliability;
        @JsonProperty("Main location")
        public String mainLocation;
        @JsonProperty("Additional location")
        public String additionalLocation;
        @JsonProperty("Extracellular location")
        public String extracellularLocation;
        @JsonProperty("Enhanced")
        public String enhanced;
        @JsonProperty("Supported")
        public String supported;
        @JsonProperty("Approved")
        public String approved;
        @JsonProperty("Uncertain")
        public String uncertain;
        @JsonProperty("Single-cell variation intensity")
        public String singleCellVariationIntensity;
        @JsonProperty("Single-cell variation spatial")
        public String singleCellVariationSpatial;
        @JsonProperty("Cell cycle dependency")
        public String CellCycleDependency;
        @JsonProperty("GO id")
        public String GoId;
}
