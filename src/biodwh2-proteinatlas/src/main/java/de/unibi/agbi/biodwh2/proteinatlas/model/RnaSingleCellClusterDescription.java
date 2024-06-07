package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaSingleCellClusterDescription")
@JsonPropertyOrder({
        "Tissue", "Cluster", "Cell type", "Cell type group", "Cell count"
})
public class RnaSingleCellClusterDescription {
    @JsonProperty("Tissue")
    @GraphProperty("tissue")
    public String tissue;
    @JsonProperty("Cluster")
    @GraphProperty("cluster")
    public String cluster;
    @JsonProperty("Cell type")
    @GraphProperty("cell_type")
    public String cellType;
    @JsonProperty("Cell type group")
    @GraphProperty("cell_type_group")
    public String cellTypeGroup;
    @JsonProperty("Cell count")
    @GraphProperty("cell_count")
    public Long cellCount;
}