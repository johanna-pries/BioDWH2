package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.Updater;
import de.unibi.agbi.biodwh2.core.exceptions.UpdaterException;
import de.unibi.agbi.biodwh2.core.model.Version;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;

public class ProteinAtlasUpdater extends Updater<ProteinAtlasDataSource> {
    static final String[] FILE_NAMES = {
            "normal_tissue.tsv.zip", "pathology.tsv.zip", "subcellular_location.tsv.zip", "rna_tissue_consensus.tsv.zip", "rna_tissue_hpa.tsv.zip",
            "rna_tissue_hpa_description.tsv.zip", "rna_brain_hpa.tsv.zip", "rna_pfc_brain_hpa.tsv.zip", "rna_tissue_gtex.tsv.zip",
            "rna_tissue_fantom.tsv.zip", "rna_single_cell_type.tsv.zip", "rna_single_cell_type_tissue.tsv.zip", "rna_single_cell_cluster_description.tsv.zip",
            "rna_single_cell_read_count.zip", "rna_brain_gtex.tsv.zip", "rna_brain_fantom.tsv.zip", "rna_pig_brain_hpa.tsv.zip", "rna_pig_brain_sample_hpa.tsv.zip",
            "rna_mouse_brain_hpa.tsv.zip", "rna_mouse_brain_sample_hpa.tsv.zip", "rna_mouse_brain_allen.tsv.zip", "rna_immune_cell.tsv.zip",
            "rna_immune_cell_sample.tsv.zip", "rna_immune_cell_sample_tpm_m.tsv.zip", "rna_immune_cell_monaco.tsv.zip", "rna_immune_cell_schmiedel.tsv.zip",
            "rna_celline_cancer.tsv.zip", "rna_celline.tsv.zip", "rna_celline_description.tsv.zip", "rna_cancer_sample.tsv.zip",
            "transcript_rna_tissue.tsv.zip", "transcript_rna_brain.tsv.zip", "transcript_rna_gtexretina.tsv.zip", "transcript_rna_immunecells.tsv.zip",
            "transcript_rna_pigbrain.tsv.zip", "transcript_rna_mousebrain.tsv.zip", "proteinatlas.tsv.zip"
    };

    public ProteinAtlasUpdater(ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

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
