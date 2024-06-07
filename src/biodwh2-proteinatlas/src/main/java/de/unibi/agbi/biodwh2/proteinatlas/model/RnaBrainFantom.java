package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaBrainFantom")
@JsonPropertyOrder({
        "Gene", "Gene name", "Brain region", "Tags per million", "Scaled tags per million", "nTPM"
})
public class RnaBrainFantom {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_brain")
    public String geneName;
    @JsonProperty("Brain region")
    @GraphProperty("brain_region")
    public String brainRegion;
    @JsonProperty("Tags per million")
    @GraphProperty("tags_per_million")
    public Float tagsPerMillion;
    @JsonProperty("Scaled tags per million")
    @GraphProperty("scaled_tags_per_million")
    public Float scaledTagsPerMillion;
    @JsonProperty("nTPM")
    @GraphProperty("n_tpm")
    public Float nTpm;
}
