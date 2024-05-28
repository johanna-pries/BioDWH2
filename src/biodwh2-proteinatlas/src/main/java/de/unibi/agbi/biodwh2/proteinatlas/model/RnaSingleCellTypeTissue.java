package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Integer readCount;
    @JsonProperty("nTPM")
    public Float nTpm;
}
