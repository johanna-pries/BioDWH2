package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaPigBrainSampleHpa {
    @JsonProperty("ENSSSCG_ID")
    public String enssscgId;
    @JsonProperty("Main_region")
    public String mainRegion;
    @JsonProperty("Subregion")
    public String subregion;
    @JsonProperty("Animal")
    public String animal;
    @JsonProperty("TPM")
    public Long tpm;
    @JsonProperty("pTPM")
    public Long pTpm;
    @JsonProperty("nTPM")
    public Long nTpm;
}
