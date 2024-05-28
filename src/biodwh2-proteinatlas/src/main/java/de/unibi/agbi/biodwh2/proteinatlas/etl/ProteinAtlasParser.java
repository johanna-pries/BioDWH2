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
        if (filePath.contains("pathways") && zipEntryName.startsWith("PA")) {
            final List<Pathway> tmpList = parseTSV(stream, Pathway.class);
            dataSource.pathways.put(zipEntry.getName().split("\\.")[0], tmpList);
        } else if (zipEntryName.equals("normal_tissue.tsv"))
            dataSource.normalTissues = parseTSV(stream, NormalTissue.class);
        else if (zipEntryName.equals("pathology.tsv"))
            dataSource.pathologies = parseTSV(stream, Pathology.class);
        else if (zipEntryName.equals("subcellular_location.tsv"))
            dataSource.subcellularLocations = parseTSV(stream, SubcellularLocation.class);
        else if (zipEntryName.equals("rna_tissue_consensus.tsv"))
            dataSource.rnaTissueConsensuses = parseTSV(stream, RnaTissueConsensus.class);
        else if (zipEntryName.equals("rna_tissue_hpa.tsv"))
            dataSource.rnaTissueHpas = parseTSV(stream, RnaTissueHpa.class);
        else if (zipEntryName.equals("rna_tissue_hpa_description.tsv"))
            dataSource.rnaTissueHpaDescriptions = parseTSV(stream, RnaTissueHpaDescription.class);
        else if (zipEntryName.equals("rna_brain_hpa.tsv"))
            dataSource.rnaBrainHpas = parseTSV(stream, RnaBrainHpa.class);
        else if (zipEntryName.equals("rna_pfc_brain_hpa.tsv"))
            dataSource.rnaPfcBrainHpas = parseTSV(stream, RnaPfcBrainHpa.class);
        else if (zipEntryName.equals("rna_tissue_gtex.tsv"))
            dataSource.rnaTissueGtexes = parseTSV(stream, RnaTissueGtex.class);
        else if (zipEntryName.equals("rna_tissue_fantom.tsv"))
            dataSource.rnaTissueFantoms = parseTSV(stream, RnaTissueFantom.class);
        else if (zipEntryName.equals("rna_single_cell_type.tsv"))
            dataSource.rnaSingleCellTypes = parseTSV(stream, RnaSingleCellType.class);
        else if (zipEntryName.equals("rna_single_cell_type_tissue.tsv"))
            dataSource.rnaSingleCellTypeTissues = parseTSV(stream, RnaSingleCellTypeTissue.class);
        else if (zipEntryName.equals("rna_single_cell_cluster_description.tsv"))
            dataSource.rnaSingleCellClusterDescriptions = parseTSV(stream, RnaSingleCellClusterDescription.class);
        else if (zipEntryName.equals("rna_single_cell_read_count.tsv"))
            dataSource.rnaSingleCellReadCounts = parseTSV(stream, RnaSingleCellReadCount.class);
        else if (zipEntryName.equals("rna_brain_gtex.tsv"))
            dataSource.rnaBrainGtexes = parseTSV(stream, RnaBrainGtex.class);
        else if (zipEntryName.equals("rna_brain_fantom.tsv"))
            dataSource.rnaBrainFantoms = parseTSV(stream, RnaBrainFantom.class);
        else if (zipEntryName.equals("rna_pig_brain_hpa.tsv"))
            dataSource.rnaPigBrainHpas = parseTSV(stream, RnaPigBrainHpa.class);
        else if (zipEntryName.equals("rna_pig_brain_sample_hpa.tsv"))
            dataSource.rnaPigBrainSampleHpas = parseTSV(stream, RnaPigBrainSampleHpa.class);
        else if (zipEntryName.equals("rna_mouse_brain_sample_hpa.tsv"))
            dataSource.rnaMouseBrainSampleHpas = parseTSV(stream, RnaMouseBrainSampleHpa.class);
        else if (zipEntryName.equals("rna_mouse_brain_allen.tsv"))
            dataSource.rnaMouseBrainAllens = parseTSV(stream, RnaMouseBrainAllen.class);
        else if (zipEntryName.equals("rna_immune_cell.tsv"))
            dataSource.rnaImmuneCells = parseTSV(stream, RnaImmuneCell.class);
        else if (zipEntryName.equals("rna_immune_cell_sample.tsv"))
            dataSource.rnaImmuneCellSamples = parseTSV(stream, RnaImmuneCellSample.class);
        else if (zipEntryName.equals("rna_immune_cell_sample_tpm_m.tsv"))
            dataSource.rnaImmuneCellSampleTpmMs = parseTSV(stream, RnaImmuneCellSampleTpmM.class);
        else if (zipEntryName.equals("rna_immune_cell_monaco.tsv"))
            dataSource.rnaImmuneCellMonacos = parseTSV(stream, RnaImmuneCellMonaco.class);
        else if (zipEntryName.equals("rna_immune_cell_schmiedel.tsv"))
            dataSource.rnaImmuneCellSchmiedels = parseTSV(stream, RnaImmuneCellSchmiedel.class);
        else if (zipEntryName.equals("rna_celline_cancer.tsv"))
            dataSource.rnaCellineCancers = parseTSV(stream, RnaCellineCancer.class);
        else if (zipEntryName.equals("rna_celline.tsv"))
            dataSource.rnaCellines = parseTSV(stream, RnaCelline.class);
        else if (zipEntryName.equals("rna_celline_description.tsv"))
            dataSource.rnaCellineDescriptions = parseTSV(stream, RnaCellineDescription.class);
        else if (zipEntryName.equals("rna_cancer_sample.tsv"))
            dataSource.rnaCancerSamples = parseTSV(stream, RnaCancerSample.class);
        else if (zipEntryName.equals("transcript_rna_tissue.tsv"))
            dataSource.transcriptRnaTissues = parseTSV(stream, TranscriptRnaTissue.class);
        else if (zipEntryName.equals("transcript_rna_brain.tsv"))
            dataSource.transcriptRnaBrains = parseTSV(stream, TranscriptRnaBrain.class);
        else if (zipEntryName.equals("transcript_rna_gtexretina.tsv"))
            dataSource.transcriptRnaGtexretinas = parseTSV(stream, TranscriptRnaGtexRetina.class);
        else if (zipEntryName.equals("transcript_rna_bloodcells.tsv"))
            dataSource.transcriptRnaBloodcells = parseTSV(stream, TranscriptRnaBloodcells.class);
        else if (zipEntryName.equals("transcript_rna_pigbrain.tsv"))
            dataSource.transcriptRnaPigbrains = parseTSV(stream, TranscriptRnaPigBrain.class);
        else if (zipEntryName.equals("transcript_rna_mousebrain.tsv"))
            dataSource.transcriptRnaMousebrains = parseTSV(stream, TranscriptRnaMouseBrain.class);
        else if (zipEntryName.equals("proteinatlas.tsv"))
            dataSource.proteinatlas = parseTSV(stream, Proteinatlas.class);
    }

    private <T> List<T> parseTSV(final InputStream stream, final Class<T> clazz) throws IOException {
        return FileUtils.openSeparatedValuesFile(stream, clazz, '\t', true, true).readAll();
    }
}
