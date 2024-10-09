package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Cancer", "High", "Medium", "Low", "Not detected", "prognostic - favorable", "unprognostic - favorable", "prognostic - unfavorable", "unprognostic - unfavorable"
})
public class Pathology {
        @JsonProperty("Gene")
        public String gene;
        @JsonProperty("Gene name")
        public String geneName;
        @JsonProperty("Cancer")
        public String cancer;
        @JsonProperty("High")
        public Long high;
        @JsonProperty("Medium")
        public Long medium;
        @JsonProperty("Low")
        public Long low;
        @JsonProperty("Not detected")
        public Long notDetected;
        @JsonProperty("prognostic - favorable")
        public Float prognosticFavorable;
        @JsonProperty("unprognostic - favorable")
        public Float unprognosticFavorable;
        @JsonProperty("prognostic - unfavorable")
        public Float prognosticUnfavorable;
        @JsonProperty("unprognostic - unfavorable")
        public Float unprognosticUnfavorable;
}
