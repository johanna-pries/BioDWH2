package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaCancerSample {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Sample")
    public String sample;
    @JsonProperty("Cancer")
    public String cancer;
    @JsonProperty("FPKM")
    public Long fpkm;
}
