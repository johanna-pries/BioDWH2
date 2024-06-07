package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaSingleCellTypeTissue")
@JsonPropertyOrder({
        "Gene", "Gene name", "Tissue", "Cluster", "Cell type", "Read count", "nTPM"
})
public class RnaSingleCellTypeTissue {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Tissue")
    @GraphProperty("tissue")
    public String tissue;
    @JsonProperty("Cluster")
    @GraphProperty("cluster")
    public String cluster;
    @JsonProperty("Cell type")
    @GraphProperty("cell_type")
    public String cellType;
    @JsonProperty("Read count")
    @GraphProperty("read_count")
    public Long readCount;
    @JsonProperty("nTPM")
    @GraphProperty("n_tpm")
    public Float nTpm;
}
