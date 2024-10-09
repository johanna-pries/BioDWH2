package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Gene", "Gene name", "Cell line", "TPM", "pTPM", "nTPM"
})
public class RnaCelline {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Cell line")
    public String cellLine;
    @JsonProperty("TPM")
    public Float tpm;
    @JsonProperty("pTPM")
    public Float pTpm;
    @JsonProperty("nTPM")
    public Float nTpm;
}
