package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("Pathology")
@JsonPropertyOrder({
        "Gene", "Gene name", "Cancer", "High", "Medium", "Low", "Not detected", "prognostic - favorable", "unprognostic - favorable", "prognostic - unfavorable", "unprognostic - unfavorable"
})
public class Pathology {
        @JsonProperty("Gene")
        @GraphProperty("gene")
        public String gene;
        @JsonProperty("Gene name")
        @GraphProperty("gene_name")
        public String geneName;
        @JsonProperty("Cancer")
        @GraphProperty("cancer")
        public String cancer;
        @JsonProperty("High")
        @GraphProperty("high")
        public Long high;
        @JsonProperty("Medium")
        @GraphProperty("medium")
        public Long medium;
        @JsonProperty("Low")
        @GraphProperty("low")
        public Long low;
        @JsonProperty("Not detected")
        @GraphProperty("not_detected")
        public Long notDetected;
        @JsonProperty("prognostic - favorable")
        @GraphProperty("prognostic_favorable")
        public Float prognosticFavorable;
        @JsonProperty("unprognostic - favorable")
        @GraphProperty("unprognostic_favorable")
        public Float unprognosticFavorable;
        @JsonProperty("prognostic - unfavorable")
        @GraphProperty("prognostic_unfavorable")
        public Float prognosticUnfavorable;
        @JsonProperty("unprognostic - unfavorable")
        @GraphProperty("unprognostic_unfavorable")
        public Float unprognosticUnfavorable;
}
