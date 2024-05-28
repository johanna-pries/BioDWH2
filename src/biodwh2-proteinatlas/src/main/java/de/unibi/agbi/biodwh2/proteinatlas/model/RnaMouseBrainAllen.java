package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaMouseBrainAllen {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Brain region")
    public String brainRegion;
    @JsonProperty("Expression energy")
    public Float expressionEnergy;
}