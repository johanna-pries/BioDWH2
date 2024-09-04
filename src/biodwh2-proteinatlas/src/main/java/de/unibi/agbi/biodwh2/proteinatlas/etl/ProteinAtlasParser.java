package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.Parser;
import de.unibi.agbi.biodwh2.core.exceptions.ParserException;
import de.unibi.agbi.biodwh2.core.exceptions.ParserFormatException;
import de.unibi.agbi.biodwh2.core.io.FileUtils;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;
import de.unibi.agbi.biodwh2.proteinatlas.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ProteinAtlasParser  extends Parser<ProteinAtlasDataSource> {
    private static final Logger LOGGER = LogManager.getLogger(ProteinAtlasParser.class);

    public ProteinAtlasParser(final ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean parse(final Workspace workspace) throws ParserException {
        for (final String filePath : ProteinAtlasUpdater.FILE_NAMES)
            parseFile(dataSource, dataSource.resolveSourceFilePath(workspace, filePath).toFile());
        return true;
    }

    private void parseFile(final ProteinAtlasDataSource dataSource, final File filePath) throws ParserFormatException {
        try (ZipFile zipFile = new ZipFile(filePath)) {
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                final String zipEntryName = zipEntry.getName();
                final InputStream stream = zipFile.getInputStream(zipEntry);
                parseZipFileEntry(dataSource, filePath.toString(), zipEntry, zipEntryName, stream);
            }
        } catch (IOException e) {
            throw new ParserFormatException("Failed to parse the file '" + filePath + "'", e);
        }
    }

    private void parseZipFileEntry(ProteinAtlasDataSource dataSource, String filePath, ZipEntry zipEntry,
                                   String zipEntryName, InputStream stream) throws IOException {
        switch (zipEntryName) {
            case "normal_tissue.tsv":
                dataSource.normalTissues = parseTSV(stream, NormalTissue.class);
                break;
            case "pathology.tsv":
                dataSource.pathologies = parseTSV(stream, Pathology.class);
                break;
            case "rna_brain_fantom.tsv":
                dataSource.rnaBrainFantoms = parseTSV(stream, RnaBrainFantom.class);
                break;
            case "rna_brain_gtex.tsv":
                dataSource.rnaBrainGtexes = parseTSV(stream, RnaBrainGtex.class);
                break;
            // FIXME: Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            /*
            case "rna_tissue_consensus.tsv":
                dataSource.rnaTissueConsensuses = parseTSV(stream, RnaTissueConsensus.class);
                break;
            case "rna_tissue_hpa.tsv":
                dataSource.rnaTissueHpas = parseTSV(stream, RnaTissueHpa.class);
                break;
            case "rna_tissue_hpa_description.tsv":
                dataSource.rnaTissueHpaDescriptions = parseTSV(stream, RnaTissueHpaDescription.class);
                break;
            case "rna_brain_hpa.tsv":
                dataSource.rnaBrainHpas = parseTSV(stream, RnaBrainHpa.class);
                break;
            case "rna_pfc_brain_hpa.tsv":
                dataSource.rnaPfcBrainHpas = parseTSV(stream, RnaPfcBrainHpa.class);
                break;
            case "rna_tissue_gtex.tsv":
                dataSource.rnaTissueGtexes = parseTSV(stream, RnaTissueGtex.class);
                break;
            case "rna_tissue_fantom.tsv":
                dataSource.rnaTissueFantoms = parseTSV(stream, RnaTissueFantom.class);
                break;
            case "rna_single_cell_type.tsv":
                dataSource.rnaSingleCellTypes = parseTSV(stream, RnaSingleCellType.class);
                break;
            case "rna_single_cell_type_tissue.tsv":
                dataSource.rnaSingleCellTypeTissues = parseTSV(stream, RnaSingleCellTypeTissue.class);
                break;
            case "rna_single_cell_cluster_description.tsv":
                dataSource.rnaSingleCellClusterDescriptions = parseTSV(stream, RnaSingleCellClusterDescription.class);
                break;
            case "rna_pig_brain_hpa.tsv":
                dataSource.rnaPigBrainHpas = parseTSV(stream, RnaPigBrainHpa.class);
                break;
            case "rna_pig_brain_sample_hpa.tsv":
                dataSource.rnaPigBrainSampleHpas = parseTSV(stream, RnaPigBrainSampleHpa.class);
                break;
            case "rna_mouse_brain_sample_hpa.tsv":
                dataSource.rnaMouseBrainSampleHpas = parseTSV(stream, RnaMouseBrainSampleHpa.class);
                break;
            case "rna_mouse_brain_hpa.tsv":
                dataSource.rnaMouseBrainHpas = parseTSV(stream, RnaMouseBrainHpa.class);
                break;
            case "rna_mouse_brain_mouse_sample.tsv":
                dataSource.rnaMouseBrainMouseSamples = parseTSV(stream, RnaMouseBrainMouseSample.class);
                break;
            case "rna_mouse_brain_allen.tsv":
                dataSource.rnaMouseBrainAllens = parseTSV(stream, RnaMouseBrainAllen.class);
                break;
            case "rna_immune_cell.tsv":
                dataSource.rnaImmuneCells = parseTSV(stream, RnaImmuneCell.class);
                break;
            case "rna_immune_cell_sample.tsv":
                dataSource.rnaImmuneCellSamples = parseTSV(stream, RnaImmuneCellSample.class);
                break;
            case "rna_immune_cell_sample_tpm_m.tsv":
                dataSource.rnaImmuneCellSampleTpmMs = parseTSV(stream, RnaImmuneCellSampleTpmM.class);
                break;
            case "rna_immune_cell_monaco.tsv":
                dataSource.rnaImmuneCellMonacos = parseTSV(stream, RnaImmuneCellMonaco.class);
                break;
            case "rna_immune_cell_schmiedel.tsv":
                dataSource.rnaImmuneCellSchmiedels = parseTSV(stream, RnaImmuneCellSchmiedel.class);
                break;
            case "rna_celline_cancer.tsv":
                dataSource.rnaCellineCancers = parseTSV(stream, RnaCellineCancer.class);
                break;
            case "rna_celline.tsv":
                dataSource.rnaCellines = parseTSV(stream, RnaCelline.class);
                break;
            case "rna_celline_description.tsv":
                dataSource.rnaCellineDescriptions = parseTSV(stream, RnaCellineDescription.class);
                break;
            case "rna_cancer_sample.tsv":
                dataSource.rnaCancerSamples = parseTSV(stream, RnaCancerSample.class);
                break;
            case "subcellular_location.tsv":
                dataSource.subcellularLocations = parseTSV(stream, SubcellularLocation.class);
                break;
            */
            
            // TODO: do this at the end
            /*
            case "transcript_rna_tissue.tsv":
                dataSource.transcriptRnaTissues = parseTSV(stream, TranscriptRnaTissue.class);
                break;
            case "transcript_rna_brain.tsv":
                dataSource.transcriptRnaBrains = parseTSV(stream, TranscriptRnaBrain.class);
                break;
            case "transcript_rna_gtexretina.tsv":
                dataSource.transcriptRnaGtexretinas = parseTSV(stream, TranscriptRnaGtexRetina.class);
                break;
            case "transcript_rna_bloodcells.tsv":
                dataSource.transcriptRnaBloodcells = parseTSV(stream, TranscriptRnaBloodcells.class);
                break;
            case "transcript_rna_pigbrain.tsv":
                dataSource.transcriptRnaPigbrains = parseTSV(stream, TranscriptRnaPigBrain.class);
                break;
            case "transcript_rna_mousebrain.tsv":
                dataSource.transcriptRnaMousebrains = parseTSV(stream, TranscriptRnaMouseBrain.class);
                break;
            */
            /*
            case "proteinatlas.tsv":
                dataSource.proteinatlas = parseTSV(stream, Proteinatlas.class);
                break;
            */
        }
    }

    private <T> List<T> parseTSV(final InputStream stream, final Class<T> clazz) throws IOException {
        return FileUtils.openSeparatedValuesFile(stream, clazz, '\t', true, true).readAll();
    }
}
