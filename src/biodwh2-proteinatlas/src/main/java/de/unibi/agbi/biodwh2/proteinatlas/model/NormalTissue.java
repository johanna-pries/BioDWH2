package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("NormalTissue")
@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Cell type", "Level", "Reliability"
})
public class NormalTissue {
        @JsonProperty("Gene")
        @GraphProperty("gene")
        public String gene;
        @JsonProperty("Gene name")
        @GraphProperty("gene_name")
        public String geneName;
        @JsonProperty("Tissue")
        @GraphProperty("tissue")
        public String tissue;
        @JsonProperty("Cell type")
        @GraphProperty("cell_type")
        public String cellType;
        @JsonProperty("Level")
        @GraphProperty("level")
        public String level;
        @JsonProperty("Reliability")
        @GraphProperty("reliability")
        public String reliability;
}
