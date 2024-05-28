package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Integer cellCount;
}