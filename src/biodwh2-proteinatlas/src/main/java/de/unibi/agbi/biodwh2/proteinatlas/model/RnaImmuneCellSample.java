package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaImmuneCellSample")
@JsonPropertyOrder({
        "Sample ID", "Donor", "Immune cell", "ENSG ID", "Gene name", "TPM", "pTPM", "nTPM"
})
public class RnaImmuneCellSample {
    @JsonProperty("Sample ID")
    @GraphProperty("sample_id")
    public String sampleId;
    @JsonProperty("Donor")
    @GraphProperty("donor")
    public String donor;
    @JsonProperty("Immune cell")
    @GraphProperty("immune_cell")
    public String immuneCell;
    @JsonProperty("ENSG ID")
    @GraphProperty("ensg_id")
    public String ensgId;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
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
