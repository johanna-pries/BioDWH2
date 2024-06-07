package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaTissueGtex")
@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "TPM", "pTPM", "nTPM"
})
public class RnaTissueGtex {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Tissue")
    @GraphProperty("tissue")
    public String tissue;
    @JsonProperty("TPM")
    @GraphProperty("tpm")
    public Float tpm;
    @JsonProperty("pTPM")
    @GraphProperty("p_tpm")
    public Float pTpm;
    @JsonProperty("nTPM")
    @GraphProperty("n_tpm")
    public Float nTpm;
}
