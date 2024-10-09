package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Cell type", "Level", "Reliability"
})
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
