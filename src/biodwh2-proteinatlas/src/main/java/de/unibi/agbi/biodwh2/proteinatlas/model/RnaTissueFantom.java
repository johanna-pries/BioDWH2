package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaTissueFantom")
@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Tags per million", "Scaled tags per million", "Normalized tags per million"
})
public class RnaTissueFantom {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Tissue")
    @GraphProperty("tissue")
    public String tissue;
    @JsonProperty("Tags per million")
    @GraphProperty("tags_per_million")
    public Float tagsPerMillion;
    @JsonProperty("Scaled tags per million")
    @GraphProperty("scaled_tags_per_million")
    public Float scaledTagsPerMillion;
    @JsonProperty("Normalized tags per million")
    @GraphProperty("normalized_tags_per_million")
    public Float normalizedTagsPerMillion;
}
