package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Subregion", "TPM", "pTPM", "nTPM"
})
public class RnaPfcBrainHpa {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Subregion")
    public String subregion;
    @JsonProperty("TPM")
    public Float tpm;
    @JsonProperty("pTPM")
    public Float pTpm;
    @JsonProperty("nTPM")
    public Float nTpm;
}