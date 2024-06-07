package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaImmuneCellSchmiedel")
@JsonPropertyOrder({
        "Gene", "Gene name", "Immune cell", "TPM"
})
public class RnaImmuneCellSchmiedel {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Immune cell")
    @GraphProperty("immune_cel")
    public String immuneCell;
    @JsonProperty("TPM")
    @GraphProperty("tpm")
    public Float tpm;
}