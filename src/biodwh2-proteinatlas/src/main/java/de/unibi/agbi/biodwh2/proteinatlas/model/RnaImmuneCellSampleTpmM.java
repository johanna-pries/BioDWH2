package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("RnaImmuneCellSampleTpmM")
@JsonPropertyOrder({
        "ENSG ID", "Gene name", "basophil donor-c", "basophil donor-d", "basophil donor-e", "basophil donor-f", "classical monocyte donor-a", "classical monocyte donor-b", "classical monocyte donor-c", "classical monocyte donor-d", "classical monocyte donor-e", "classical monocyte donor-f", "eosinophil donor-a", "eosinop"
})
public class RnaImmuneCellSampleTpmM {
    @JsonProperty("ENSG ID")
    @GraphProperty("ensg_id")
    public String ensgId;
    @JsonProperty("Gene name")
    @GraphProperty("gene_name")
    public String geneName;
    @JsonProperty("basophil donor-c")
    @GraphProperty("basophil_donor_c")
    public String basophilDonorC;
    @JsonProperty("basophil donor-d")
    @GraphProperty("basophil_donor_d")
    public String basophilDonorD;
    @JsonProperty("basophil donor-e")
    @GraphProperty("basophil_donor_e")
    public String basophilDonorE;
    @JsonProperty("basophil donor-f")
    @GraphProperty("basophil_donor_f")
    public String basophilDonorF;
    @JsonProperty("classical monocyte donor-a")
    @GraphProperty("classical_monocyte_donor_a")
    public String classicalMonocyteDonorA;
    @JsonProperty("classical monocyte donor-b")
    @GraphProperty("classical_monocyte_donor_b")
    public String classicalMonocyteDonorB;
    @JsonProperty("classical monocyte donor-c")
    @GraphProperty("classical_monocyte_donor_c")
    public String classicalMonocyteDonorC;
    @JsonProperty("classical monocyte donor-d")
    @GraphProperty("classical_monocyte_donor_d")
    public String classicalMonocyteDonorD;
    @JsonProperty("classical monocyte donor-e")
    @GraphProperty("classical_monocyte_donor_e")
    public String classicalMonocyteDonorE;
    @JsonProperty("classical monocyte donor-f")
    @GraphProperty("classical_monocyte_donor_f")
    public String classicalMonocyteDonorF;
    @JsonProperty("eosinophil donor-a")
    @GraphProperty("eosinophil_donor_a")
    public String eosinophilDonorA;
    @JsonProperty("eosinop")
    @GraphProperty("eosinop")
    public String eosinop;
}
