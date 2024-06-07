package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaSingleCellType")
@JsonPropertyOrder({
        "Gene", "Gene name", "Cell type", "nTPM"
})
public class RnaSingleCellType {
    @JsonProperty("Gene")
    @GraphProperty("gene")
    public String gene;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("Cell type")
    @GraphProperty("cell_type")
    public String cellType;
    @JsonProperty("nTPM")
    @GraphProperty("n_pm")
    public Float nTpm;
}
