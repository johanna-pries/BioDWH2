package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaCancerSample")
@JsonPropertyOrder({
        "Gene", "Sample", "Cancer", "FPKM"
})
public class RnaCancerSample {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Sample")
    public String sample;
    @JsonProperty("Cancer")
    public String cancer;
    @JsonProperty("FPKM")
    public Float fpkm;
}
