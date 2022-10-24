package de.unibi.agbi.biodwh2.clinicaltrialsgov.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum {
    PRINCIPAL_INVESTIGATOR("Principal Investigator"),
    SUB_INVESTIGATOR("Sub-Investigator"),
    STUDY_CHAIR("Study Chair"),
    STUDY_DIRECTOR("Study Director");

    RoleEnum(String value) {
        this.value = value;
    }

    public final String value;

    @JsonValue
    public String toValue() {
        return value;
    }
}
