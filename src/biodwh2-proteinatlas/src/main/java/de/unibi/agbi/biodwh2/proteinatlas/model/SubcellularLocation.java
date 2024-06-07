package de.unibi.agbi.biodwh2.proteinatlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.unibi.agbi.biodwh2.core.model.graph.GraphNodeLabel;
import de.unibi.agbi.biodwh2.core.model.graph.GraphProperty;

@GraphNodeLabel("SubcellularLocation")
@JsonPropertyOrder({
        "Gene", "Gene name", "Reliability", "Main location", "Additional location", "Extracellular location", "Enhanced", "Supported", "Approved", "Uncertain", "Single-cell variation intensity", "Single-cell variation spatial", "Cell cycle dependency", "GO id"
})
public class SubcellularLocation {
        @JsonProperty("Gene")
        @GraphProperty("gene")
        public String gene;
        @JsonProperty("Gene name")
        @GraphProperty("gene_name")
        public String geneName;
        @JsonProperty("Reliability")
        @GraphProperty("reliability")
        public String reliability;
        @JsonProperty("Main location")
        @GraphProperty("main_location")
        public String mainLocation;
        @JsonProperty("Additional location")
        @GraphProperty("additional_location")
        public String additionalLocation;
        @JsonProperty("Extracellular location")
        @GraphProperty("extracellular_location")
        public String extracellularLocation;
        @JsonProperty("Enhanced")
        @GraphProperty("enhanced")
        public String enhanced;
        @JsonProperty("Supported")
        @GraphProperty("supported")
        public String supported;
        @JsonProperty("Approved")
        @GraphProperty("approved")
        public String approved;
        @JsonProperty("Uncertain")
        @GraphProperty("uncertain")
        public String uncertain;
        @JsonProperty("Single-cell variation intensity")
        @GraphProperty("single_cell_variation_intensity")
        public String singleCellVariationIntensity;
        @JsonProperty("Single-cell variation spatial")
        @GraphProperty("single_cell_variation_spatial")
        public String singleCellVariationSpatial;
        @JsonProperty("Cell cycle dependency")
        @GraphProperty("cell_cycle_dependency")
        public String CellCycleDependency;
        @JsonProperty("GO id")
        @GraphProperty("go_id")
        public String GoId;
}
