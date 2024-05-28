package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pathology {
        @JsonProperty("Gene")
        public String gene;
        @JsonProperty("Gene name")
        public String geneName;
        @JsonProperty("Cancer")
        public String cancer;
        @JsonProperty("High")
        public Integer high;
        @JsonProperty("Medium")
        public Integer medium;
        @JsonProperty("Low")
        public Integer low;
        @JsonProperty("Not detected")
        public Integer notDetected;
        @JsonProperty("prognostic - favorable")
        public Float prognosticFavorable;
        @JsonProperty("unprognostic - favorable")
        public Float unprognosticFavorable;
        @JsonProperty("prognostic - unfavorable")
        public Float prognosticUnfavorable;
        @JsonProperty("unprognostic - unfavorable")
        public Float unprognosticUnfavorable;
}
