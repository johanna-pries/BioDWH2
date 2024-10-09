package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaTissueHpaDescription")
@JsonPropertyOrder({
        "Tissue", "Tissue group", "Organ"
})
public class RnaTissueHpaDescription {
    @JsonProperty("Tissue")
    public String tissue;
    @JsonProperty("Tissue group")
    public String tissueGroup;
    @JsonProperty("Organ")
    public String organ;
}
