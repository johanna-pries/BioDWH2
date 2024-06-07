package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaPigBrainSampleHpa")
@JsonPropertyOrder({
        "ENSSSCG_ID", "Main_region", "Subregion", "Animal", "TPM", "pTPM", "nTPM"
})
public class RnaPigBrainSampleHpa {
    @JsonProperty("ENSSSCG_ID")
    @GraphProperty("enssscg_id")
    public String enssscgId;
    @JsonProperty("Main_region")
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
    @JsonProperty("nTPM")
    @GraphProperty("n_tpm")
    public Float nTpm;
}
