package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaCancerSample")
@JsonPropertyOrder({
        "Gene", "Sample", "Cancer", "FPKM"
})
public class RnaCancerSample {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Sample")
    @GraphProperty("sample")
    public String sample;
    @JsonProperty("Cancer")
    @GraphProperty("cancer")
    public String cancer;
    @JsonProperty("FPKM")
    @GraphProperty("fpkm")
    public Float fpkm;
}
