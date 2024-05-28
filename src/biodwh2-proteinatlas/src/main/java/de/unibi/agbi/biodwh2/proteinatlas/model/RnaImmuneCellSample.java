package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Long tpm;
    @JsonProperty("pTPM")
    public Long pTpm;
    @JsonProperty("nTPM")
    public Long nTpm;
}
