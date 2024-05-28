package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NormalTissue {
        @JsonProperty("Gene")
        public String gene;
        @JsonProperty("Gene name")
        public String geneName;
        @JsonProperty("Tissue")
        public String tissue;
        @JsonProperty("Cell type")
        public String cellType;
        @JsonProperty("Level")
        public String level;
        @JsonProperty("Reliability")
        public String reliability;
}
