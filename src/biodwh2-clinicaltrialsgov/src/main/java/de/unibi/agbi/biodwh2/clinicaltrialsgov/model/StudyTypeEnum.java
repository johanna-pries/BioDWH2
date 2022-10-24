package de.unibi.agbi.biodwh2.clinicaltrialsgov.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StudyTypeEnum {
    EXPANDED_ACCESS("Expanded Access"),
    INTERVENTIONAL("Interventional"),
    N_A("N/A"),
    OBSERVATIONAL("Observational"),
    OBSERVATIONAL_PATIENT_REGISTRY("Observational [Patient Registry]");

    StudyTypeEnum(String value) {
        this.value = value;
    }

    public final String value;

    @JsonValue
    public String toValue() {
        return value;
    }
}
