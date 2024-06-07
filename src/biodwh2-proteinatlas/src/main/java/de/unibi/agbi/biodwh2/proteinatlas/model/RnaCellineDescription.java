package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaCellineDescription")
@JsonPropertyOrder({
        "Cell line", "Disease", "Disease subtype", "Cellosaurus ID", "Patient", "Primary/Metastasis", "Sample collection site"
})
public class RnaCellineDescription {
    @JsonProperty("Cell line")
    @GraphProperty("cell_line")
    public String cellLine;
    @JsonProperty("Disease")
    @GraphProperty("disease")
    public String disease;
    @JsonProperty("Disease subtype")
    @GraphProperty("disease_subtype")
    public String diseaseSubtype;
    @JsonProperty("Cellosaurus ID")
    @GraphProperty("cellosaurus_id")
    public String cellosaurusId;
    @JsonProperty("Patient")
    @GraphProperty("patient")
    public String patient;
    @JsonProperty("Primary/Metastasis")
    @GraphProperty("primary_metastasis")
    public String primaryMetastasis;
    @JsonProperty("Sample collection site")
    @GraphProperty("sample_collection_site")
    public String sampleCollectionSite;
}
