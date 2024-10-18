package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.Updater;
import de.unibi.agbi.biodwh2.core.exceptions.UpdaterException;
import de.unibi.agbi.biodwh2.core.model.Version;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProteinAtlasUpdater extends Updater<ProteinAtlasDataSource> {

    private static final String VERSION_URL = "https://www.proteinatlas.org/about/releases";
    private static final Pattern VERSION_PATTERN = Pattern.compile("Protein Atlas version ([0-9]+)\\.([0-9]+)");

    static final String[] FILE_NAMES = {
            "normal_tissue.tsv.zip", "pathology.tsv.zip", "subcellular_location.tsv.zip", "rna_tissue_consensus.tsv.zip",
            "rna_tissue_hpa.tsv.zip", "rna_tissue_hpa_description.tsv.zip", "rna_brain_hpa.tsv.zip",
            "rna_pfc_brain_hpa.tsv.zip", "rna_tissue_gtex.tsv.zip", "rna_tissue_fantom.tsv.zip",
            "rna_single_cell_type.tsv.zip", "rna_single_cell_type_tissue.tsv.zip",
            "rna_single_cell_cluster_description.tsv.zip", "rna_brain_gtex.tsv.zip", "rna_brain_fantom.tsv.zip",
            "rna_pig_brain_hpa.tsv.zip", "rna_pig_brain_sample_hpa.tsv.zip", "rna_mouse_brain_hpa.tsv.zip",
            "rna_mouse_brain_sample_hpa.tsv.zip", "rna_mouse_brain_allen.tsv.zip", "rna_immune_cell.tsv.zip",
            "rna_immune_cell_sample.tsv.zip", "rna_immune_cell_monaco.tsv.zip", "rna_immune_cell_schmiedel.tsv.zip",
            "rna_celline_cancer.tsv.zip", "rna_celline.tsv.zip", "rna_celline_description.tsv.zip",
            "rna_cancer_sample.tsv.zip"
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
    static final String RNA_PIG_BRAIN_SAMPLE_HPA_ZIP_FILE_NAME = "rna_pig_brain_sample_hpa.tsv.zip";
    static final String RNA_PIG_BRAIN_SAMPLE_HPA_TARGET_FILE_NAME = "rna_pig_brain_sample_hpa.tsv";
    static final String RNA_PIG_BRAIN_PIG_SAMPLE_TARGET_FILE_NAME = "rna_pigbrain_pig_sample.tsv";
    static final String RNA_MOUSE_BRAIN_HPA_FILE_NAME = "rna_mouse_brain_hpa.tsv.zip";
    static final String RNA_MOUSE_BRAIN_ALLEN_FILE_NAME = "rna_mouse_brain_allen.tsv.zip";
    static final String RNA_MOUSE_BRAIN_SAMPLE_HPA_ZIP_FILE_NAME = "rna_mouse_brain_sample_hpa.tsv.zip";
    static final String RNA_MOUSE_BRAIN_SAMPLE_HPA_TARGET_FILE_NAME = "rna_mouse_brain_sample_hpa.tsv";
    static final String RNA_MOUSE_BRAIN_MOUSE_SAMPLE_TARGET_FILE_NAME = "rna_mousebrain_mouse_sample.tsv";
    static final String RNA_IMMUNE_CELL_FILE_NAME = "rna_immune_cell.tsv.zip";
    static final String RNA_IMMUNE_CELL_SAMPLE_FILE_NAME = "rna_immune_cell_sample.tsv.zip";
    static final String RNA_IMMUNE_CELL_MONACO_FILE_NAME = "rna_immune_cell_monaco.tsv.zip";
    static final String RNA_IMMUNE_CELL_SCHMIEDEL_FILE_NAME = "rna_immune_cell_schmiedel.tsv.zip";
    static final String RNA_CELLINE_CANCER_FILE_NAME = "rna_celline_cancer.tsv.zip";
    static final String RNA_CELLINE_FILE_NAME = "rna_celline.tsv.zip";
    static final String RNA_CELLINE_DESCRIPTION_FILE_NAME = "rna_celline_description.tsv.zip";
    static final String RNA_CANCER_SAMPLE_ZIP_FILE_NAME = "rna_cancer_sample.tsv.zip";
    static final String RNA_CANCER_SAMPLE_TARGET_FILE_NAME = "rna_cancer_sample_109.tsv";


    public ProteinAtlasUpdater(ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Version getNewestVersion(final Workspace workspace) throws UpdaterException {
        final String html = getWebsiteSource(VERSION_URL);
        final Matcher matcher = VERSION_PATTERN.matcher(html);
        if (matcher.find())
            return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        return null;
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
