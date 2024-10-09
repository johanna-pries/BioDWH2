package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaSingleCellClusterDescription")
@JsonPropertyOrder({
        "Tissue", "Cluster", "Cell type", "Cell type group", "Cell count"
})
public class RnaSingleCellClusterDescription {
    @JsonProperty("Tissue")
    public String tissue;
    @JsonProperty("Cluster")
    public String cluster;
    @JsonProperty("Cell type")
    public String cellType;
    @JsonProperty("Cell type group")
    public String cellTypeGroup;
    @JsonProperty("Cell count")
    public Long cellCount;
}