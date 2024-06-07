package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaMouseBrainAllen")
@JsonPropertyOrder({
        "Gene", "Gene name", "Brain region", "Expression energy"
})
public class RnaMouseBrainAllen {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Brain region")
    @GraphProperty("brain_region")
    public String brainRegion;
    @JsonProperty("Expression energy")
    @GraphProperty("expression_energy")
    public Float expressionEnergy;
}