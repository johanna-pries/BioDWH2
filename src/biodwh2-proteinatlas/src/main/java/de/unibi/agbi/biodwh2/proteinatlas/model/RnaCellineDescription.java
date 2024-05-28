package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
