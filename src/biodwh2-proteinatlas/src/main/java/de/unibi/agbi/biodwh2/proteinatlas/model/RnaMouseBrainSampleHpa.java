package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaMouseBrainSampleHpa")
@JsonPropertyOrder({
        "ENSMUSG_ID", "Main_region", "Subregion", "Animal", "TPM", "pTPM", "nTPM"
})
public class RnaMouseBrainSampleHpa {
    @JsonProperty("ENSMUSG_ID")
    public String ensmusgId;
    @JsonProperty("Main_region")
    public String mainRegion;
    @JsonProperty("Subregion")
    public String subregion;
    @JsonProperty("Animal")
    public String animal;
    @JsonProperty("TPM")
    public Float tpm;
    @JsonProperty("pTPM")
    public Float pTpm;
    @JsonProperty("nTPM")
    public Float nTpm;
}