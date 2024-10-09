package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Brain region", "Tags per million", "Scaled tags per million", "nTPM"
})
public class RnaBrainFantom {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Brain region")
    public String brainRegion;
    @JsonProperty("Tags per million")
    public Float tagsPerMillion;
    @JsonProperty("Scaled tags per million")
    public Float scaledTagsPerMillion;
    @JsonProperty("nTPM")
    public Float nTpm;
}
