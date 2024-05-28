package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaTissueConsensus {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Tissue")
    public String tissue;
    @JsonProperty("nTPM")
    public Float nTpm;
}
