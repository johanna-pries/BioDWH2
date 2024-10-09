package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;

@GraphNodeLabel("RnaCellineDescription")
@JsonPropertyOrder({
        "Cell line", "Disease", "Disease subtype", "Cellosaurus ID", "Patient", "Primary/Metastasis", "Sample collection site"
})
public class RnaCellineDescription {
    @JsonProperty("Cell line")
    public String cellLine;
    @JsonProperty("Disease")
    public String disease;
    @JsonProperty("Disease subtype")
    public String diseaseSubtype;
    @JsonProperty("Cellosaurus ID")
    public String cellosaurusId;
    @JsonProperty("Patient")
    public String patient;
    @JsonProperty("Primary/Metastasis")
    public String primaryMetastasis;
    @JsonProperty("Sample collection site")
    public String sampleCollectionSite;
}
