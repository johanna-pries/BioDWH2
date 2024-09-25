package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.Updater;
import de.unibi.agbi.biodwh2.core.exceptions.UpdaterException;
import de.unibi.agbi.biodwh2.core.model.Version;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;

public class ProteinAtlasUpdater extends Updater<ProteinAtlasDataSource> {

    static final String[] FILE_NAMES = {
            "normal_tissue.tsv.zip", "pathology.tsv.zip", "subcellular_location.tsv.zip", "rna_tissue_consensus.tsv.zip",
            "rna_tissue_hpa.tsv.zip", "rna_tissue_hpa_description.tsv.zip", "rna_brain_hpa.tsv.zip",
            "rna_pfc_brain_hpa.tsv.zip", "rna_tissue_gtex.tsv.zip", "rna_tissue_fantom.tsv.zip",
            "rna_single_cell_type.tsv.zip", "rna_single_cell_type_tissue.tsv.zip",
            "rna_single_cell_cluster_description.tsv.zip", "rna_brain_gtex.tsv.zip", "rna_brain_fantom.tsv.zip",
            "rna_pig_brain_hpa.tsv.zip", "rna_pig_brain_sample_hpa.tsv.zip", "rna_mouse_brain_hpa.tsv.zip",
            "rna_mouse_brain_sample_hpa.tsv.zip", "rna_mouse_brain_allen.tsv.zip", "rna_immune_cell.tsv.zip",
            "rna_immune_cell_sample.tsv.zip", "rna_immune_cell_sample_tpm_m.tsv.zip", "rna_immune_cell_monaco.tsv.zip",
            "rna_immune_cell_schmiedel.tsv.zip", "rna_celline_cancer.tsv.zip", "rna_celline.tsv.zip",
            "rna_celline_description.tsv.zip", "rna_cancer_sample.tsv.zip", "transcript_rna_tissue.tsv.zip",
            "transcript_rna_brain.tsv.zip", "transcript_rna_gtexretina.tsv.zip", "transcript_rna_immunecells.tsv.zip",
            "transcript_rna_pigbrain.tsv.zip", "transcript_rna_mousebrain.tsv.zip", "proteinatlas.tsv.zip"
    };

    static final String NORMAL_TISSUE_FILE_NAME = "normal_tissue.tsv.zip";
    static final String PATHOLOGY_FILE_NAME = "pathology.tsv.zip";
    static final String SUBCELLULAR_LOCATION_FILE_NAME = "subcellular_location.tsv.zip";
    static final String RNA_TISSUE_CONSENSUS_FILE_NAME = "rna_tissue_consensus.tsv.zip";
    static final String RNA_TISSUE_HPA_FILE_NAME = "rna_tissue_hpa.tsv.zip";
    static final String RNA_TISSUE_HPA_DESCRIPTION_FILE_NAME = "rna_tissue_hpa_description.tsv.zip";
    static final String RNA_BRAIN_HPA_FILE_NAME = "rna_brain_hpa.tsv.zip";
    static final String RNA_PFC_BRAIN_HPA_FILE_NAME = "rna_pfc_brain_hpa.tsv.zip";
    static final String RNA_TISSUE_GTEX_FILE_NAME = "rna_tissue_gtex.tsv.zip";
    static final String RNA_TISSUE_FANTOM_FILE_NAME = "rna_tissue_fantom.tsv.zip";
    static final String RNA_SINGLE_CELL_TYPE_FILE_NAME = "rna_single_cell_type.tsv.zip";
    static final String RNA_SINGLE_CELL_TYPE_TISSUE_FILE_NAME = "rna_single_cell_type_tissue.tsv.zip";
    static final String RNA_SINGLE_CELL_CLUSTER_DESCRIPTION_FILE_NAME = "rna_single_cell_cluster_description.tsv.zip";
    static final String RNA_BRAIN_GTEX_FILE_NAME = "rna_brain_gtex.tsv.zip";
    static final String RNA_BRAIN_FANTOM_FILE_NAME = "rna_brain_fantom.tsv.zip";
    static final String RNA_PIG_BRAIN_HPA_FILE_NAME = "rna_pig_brain_hpa.tsv.zip";
    static final String RNA_PIG_BRAIN_SAMPLE_HPA_FILE_NAME = "rna_pig_brain_sample_hpa.tsv.zip";
    static final String RNA_MOUSE_BRAIN_HPA_FILE_NAME = "rna_mouse_brain_hpa.tsv.zip";
    static final String RNA_MOUSE_BRAIN_SAMPLE_HPA_FILE_NAME = "rna_mouse_brain_sample_hpa.tsv.zip";
    static final String RNA_MOUSE_BRAIN_ALLEN_FILE_NAME = "rna_mouse_brain_allen.tsv.zip";
    static final String RNA_MOUSE_BRAIN_MOUSE_SAMPLE_FILE_NAME = "rna_mouse_brain_mouse_sample.tsv.zip";
    static final String RNA_IMMUNE_CELL_FILE_NAME = "rna_immune_cell.tsv.zip";
    static final String RNA_IMMUNE_CELL_SAMPLE_FILE_NAME = "rna_immune_cell_sample.tsv.zip";
    // The following file seems to be damaged.
    // static final String RNA_IMMUNE_CELL_SAMPLE_TPM_M_FILE_NAME = "rna_immune_cell_sample_tpm_m.tsv.zip";
    static final String RNA_IMMUNE_CELL_MONACO_FILE_NAME = "rna_immune_cell_monaco.tsv.zip";
    static final String RNA_IMMUNE_CELL_SCHMIEDEL_FILE_NAME = "rna_immune_cell_schmiedel.tsv.zip";
    static final String RNA_CELLINE_CANCER_FILE_NAME = "rna_celline_cancer.tsv.zip";
    static final String RNA_CELLINE_FILE_NAME = "rna_celline.tsv.zip";
    static final String RNA_CELLINE_DESCRIPTION_FILE_NAME = "rna_celline_description.tsv.zip";
    static final String RNA_CANCER_SAMPLE_FILE_NAME = "rna_cancer_sample.tsv.zip";
    // TODO: do this at the end
    static final String TRANSCRIPT_RNA_TISSUE_FILE_NAME = "transcript_rna_tissue.tsv.zip";
    static final String TRANSCRIPT_RNA_BRAIN_FILE_NAME = "transcript_rna_brain.tsv.zip";
    static final String TRANSCRIPT_RNA_GTEX_RETINA_FILE_NAME = "transcript_rna_gtexretina.tsv.zip";
    static final String TRANSCRIPT_RNA_IMMUNE_CELLS_FILE_NAME = "transcript_rna_immunecells.tsv.zip";
    static final String TRANSCRIPT_RNA_PIG_BRAIN_FILE_NAME = "transcript_rna_pigbrain.tsv.zip";
    static final String TRANSCRIPT_RNA_MOUSE_BRAIN_FILE_NAME = "transcript_rna_mousebrain.tsv.zip";

    static final String PROTEIN_ATLAS_FILE_NAME = "proteinatlas.tsv.zip";


    public ProteinAtlasUpdater(ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

    // TODO: Automatically get the newest version.
    @Override
    protected Version getNewestVersion(final Workspace workspace) {
        return new Version(2023, 6, 19);
    }

    @Override
    protected boolean tryUpdateFiles(final Workspace workspace) throws UpdaterException {
        for (final String fileName : FILE_NAMES)
            downloadFileAsBrowser(workspace, "https://www.proteinatlas.org/download/" + fileName, fileName);
        return true;
    }

    @Override
    protected String[] expectedFileNames() {
        return FILE_NAMES;
    }
}
