package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaSingleCellTypeTissue")
@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Cluster", "Cell type", "Read count", "nTPM"
})
public class RnaSingleCellTypeTissue {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Tissue")
    public String tissue;
    @JsonProperty("Cluster")
    public String cluster;
    @JsonProperty("Cell type")
    public String cellType;
    @JsonProperty("Read count")
    public Long readCount;
    @JsonProperty("nTPM")
    public Float nTpm;
}
