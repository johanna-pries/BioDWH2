package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaCelline")
@JsonPropertyOrder({
        "Gene", "Gene name", "Cell line", "TPM", "pTPM", "nTPM"
})
public class RnaCelline {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Cell line")
    @GraphProperty("cell_line")
    public String cellLine;
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
