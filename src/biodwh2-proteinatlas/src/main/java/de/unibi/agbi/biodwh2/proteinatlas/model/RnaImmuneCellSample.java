package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaImmuneCellSample")
@JsonPropertyOrder({
        "Sample ID", "Donor", "Immune cell", "ENSG ID", "Gene name", "TPM", "pTPM", "nTPM"
})
public class RnaImmuneCellSample {
    @JsonProperty("Sample ID")
    public String sampleId;
    @JsonProperty("Donor")
    public String donor;
    @JsonProperty("Immune cell")
    public String immuneCell;
    @JsonProperty("ENSG ID")
    public String ensgId;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("TPM")
    public Float tpm;
    @JsonProperty("pTPM")
    public Float pTpm;
    @JsonProperty("nTPM")
    public Float nTpm;
}
