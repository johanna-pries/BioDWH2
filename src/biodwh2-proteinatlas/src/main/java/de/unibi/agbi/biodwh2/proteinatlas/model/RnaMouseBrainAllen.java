package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaMouseBrainAllen")
@JsonPropertyOrder({
        "Gene", "Gene name", "Brain region", "Expression energy"
})
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