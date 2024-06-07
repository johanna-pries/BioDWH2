package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaMouseBrainMouseSample")
@JsonPropertyOrder({
        "ENSMUSG_ID", "Main region", "Subregion", "Animal", "TPM", "pTPM"
})
public class RnaMouseBrainMouseSample {
    @JsonProperty("ENSMUSG_ID")
    @GraphProperty("ensmusg_id")
    public String ensmusgId;
    @JsonProperty("Main region")
    @GraphProperty("main_region")
    public String mainRegion;
    @JsonProperty("Subregion")
    @GraphProperty("subregion")
    public String subregion;
    @JsonProperty("Animal")
    @GraphProperty("animal")
    public String animal;
    @JsonProperty("TPM")
    @GraphProperty("tpm")
    public Float tpm;
    @JsonProperty("pTPM")
    @GraphProperty("p_tpm")
    public Float pTpm;
}