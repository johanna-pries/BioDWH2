package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaTissueHpaDescription")
public class RnaTissueHpaDescription {
    @JsonPropertyOrder({
            "Tissue", "Tissue group", "Organ"
    })
    @JsonProperty("Tissue")
    @GraphProperty("tissue")
    public String tissue;
    @JsonProperty("Tissue group")
    @GraphProperty("tissue_group")
    public String tissueGroup;
    @JsonProperty("Organ")
    @GraphProperty("organ")
    public String organ;
}
