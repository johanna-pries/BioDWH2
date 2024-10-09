package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaSingleCellType")
@JsonPropertyOrder({
        "Gene", "Gene name", "Cell type", "nTPM"
})
public class RnaSingleCellType {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Cell type")
    public String cellType;
    @JsonProperty("nTPM")
    public Float nTpm;
}
