package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Tags per million", "Scaled tags per million", "Normalized tags per million"
})
public class RnaTissueFantom {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Tissue")
    public String tissue;
    @JsonProperty("Tags per million")
    public Float tagsPerMillion;
    @JsonProperty("Scaled tags per million")
    public Float scaledTagsPerMillion;
    @JsonProperty("Normalized tags per million")
    public Float normalizedTagsPerMillion;
}
