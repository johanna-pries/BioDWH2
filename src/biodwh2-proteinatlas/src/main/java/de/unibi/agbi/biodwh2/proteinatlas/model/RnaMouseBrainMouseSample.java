package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaMouseBrainMouseSample {
    @JsonProperty("ENSMUSG_ID")
    public String ensmusgId;
    @JsonProperty("Main region")
    public String mainRegion;
    @JsonProperty("Subregion")
    public String subregion;
    @JsonProperty("Animal")
    public String animal;
    @JsonProperty("TPM")
    public Long tpm;
    @JsonProperty("pTPM")
    public Long pTpm;
}