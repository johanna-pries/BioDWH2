package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RnaImmuneCellSchmiedel {
    @JsonProperty("Gene")
    public String gene;
    @JsonProperty("Gene name")
    public String geneName;
    @JsonProperty("Immune cell")
    public String immuneCell;
    @JsonProperty("TPM")
    public Float tpm;
}