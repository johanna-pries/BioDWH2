package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaPigBrainHpa")
@JsonPropertyOrder({
        "Gene", "Gene name", "Brain region", "TPM", "pTPM", "nTPM"
})
public class RnaPigBrainHpa {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Brain region")
    @GraphProperty("brain_region")
    public String brainRegion;
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
