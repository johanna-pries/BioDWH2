package de.unibi.agbi.biodwh2.proteinatlas;

import de.unibi.agbi.biodwh2.core.DataSource;
import de.unibi.agbi.biodwh2.core.DevelopmentState;
import de.unibi.agbi.biodwh2.core.etl.*;
import de.unibi.agbi.biodwh2.core.text.License;
import de.unibi.agbi.biodwh2.proteinatlas.etl.ProteinAtlasGraphExporter;
import de.unibi.agbi.biodwh2.proteinatlas.etl.ProteinAtlasMappingDescriber;
import de.unibi.agbi.biodwh2.proteinatlas.etl.ProteinAtlasParser;
import de.unibi.agbi.biodwh2.proteinatlas.etl.ProteinAtlasUpdater;
import de.unibi.agbi.biodwh2.proteinatlas.model.*;

import java.util.List;

public class ProteinAtlasDataSource extends DataSource {

    public List<NormalTissue> normalTissues;
    public List<Pathology> pathologies;
    public List<SubcellularLocation> subcellularLocations;
    public List<RnaTissueConsensus> rnaTissueConsensuses;
    public List<RnaTissueHpa> rnaTissueHpas;
    public List<RnaTissueHpaDescription> rnaTissueHpaDescriptions;
    public List<RnaBrainHpa> rnaBrainHpas;
    public List<RnaPfcBrainHpa> rnaPfcBrainHpas;
    public List<RnaTissueGtex> rnaTissueGtexes;
    public List<RnaTissueFantom> rnaTissueFantoms;
    public List<RnaSingleCellType> rnaSingleCellTypes;
    public List<RnaSingleCellTypeTissue> rnaSingleCellTypeTissues;
    public List<RnaSingleCellClusterDescription> rnaSingleCellClusterDescriptions;
    public List<RnaSingleCellReadCount> rnaSingleCellReadCounts;
    public List<RnaBrainGtex> rnaBrainGtexes;
    public List<RnaBrainFantom> rnaBrainFantoms;
    public List<RnaPigBrainHpa> rnaPigBrainHpas;
    public List<RnaPigBrainSampleHpa> rnaPigBrainSampleHpas;
    public List<RnaMouseBrainSampleHpa> rnaMouseBrainSampleHpas;
    public List<RnaMouseBrainAllen> rnaMouseBrainAllens;
    public List<RnaMouseBrainHpa> rnaMouseBrainHpas;
    public List<RnaMouseBrainMouseSample> rnaMouseBrainMouseSamples;
    public List<RnaImmuneCell> rnaImmuneCells;
    public List<RnaImmuneCellSample> rnaImmuneCellSamples;
    public List<RnaImmuneCellSampleTpmM> rnaImmuneCellSampleTpmMs;
    public List<RnaImmuneCellMonaco> rnaImmuneCellMonacos;
    public List<RnaImmuneCellSchmiedel> rnaImmuneCellSchmiedels;
    public List<RnaCellineCancer> rnaCellineCancers;
    public List<RnaCelline> rnaCellines;
    public List<RnaCellineDescription> rnaCellineDescriptions;
    public List<RnaCancerSample> rnaCancerSamples;
    // delete?
    public List<TranscriptRnaTissue> transcriptRnaTissues;
    public List<TranscriptRnaBrain> transcriptRnaBrains;
    public List<TranscriptRnaGtexRetina> transcriptRnaGtexretinas;
    public List<TranscriptRnaBloodcells> transcriptRnaBloodcells;
    public List<TranscriptRnaPigBrain> transcriptRnaPigbrains;
    public List<TranscriptRnaMouseBrain> transcriptRnaMousebrains;
    public List<Proteinatlas> proteinatlas;

    @Override
    public String getId() {
        return "ProteinAtlas";
    }

    // don't know what this is
    @Override
    public String getLicense() {
        return License.CC_BY_SA_4_0.getName();
    }

    @Override
    public String getFullName() {
        return "The Human Protein Atlas";
    }

    @Override
    public String getDescription() {
        return "The Human Protein Atlas is a Swedish-based program initiated in 2003 with the aim to map all the human proteins in cells, tissues, and organs using an integration of various omics technologies, including antibody-based imaging, mass spectrometry-based proteomics, transcriptomics, and systems biology.";
    }

    @Override
    public DevelopmentState getDevelopmentState() {
        return DevelopmentState.InDevelopment;
    }

    @Override
    public Updater<ProteinAtlasDataSource> getUpdater() {
        return new ProteinAtlasUpdater(this);
    }

    @Override
    public Parser<ProteinAtlasDataSource> getParser() {
        return new ProteinAtlasParser(this);
    }

    @Override
    protected GraphExporter<ProteinAtlasDataSource> getGraphExporter() {
        return new ProteinAtlasGraphExporter(this);
    }

    @Override
    public MappingDescriber getMappingDescriber() {
        return new ProteinAtlasMappingDescriber(this);
    }

    @Override
    protected void unloadData() {
        normalTissues = null;
        pathologies = null;
        subcellularLocations = null;
        rnaTissueConsensuses = null;
        rnaTissueHpas = null;
        rnaTissueHpaDescriptions = null;
        rnaBrainHpas = null;
        rnaPfcBrainHpas = null;
        rnaTissueGtexes = null;
        rnaTissueFantoms = null;
        rnaSingleCellTypes = null;
        rnaSingleCellTypeTissues = null;
        rnaSingleCellClusterDescriptions = null;
        rnaSingleCellReadCounts = null;
        rnaBrainGtexes = null;
        rnaBrainFantoms = null;
        rnaPigBrainHpas = null;
        rnaPigBrainSampleHpas = null;
        rnaMouseBrainSampleHpas = null;
        rnaMouseBrainHpas = null;
        rnaMouseBrainMouseSamples = null;
        rnaImmuneCells = null;
        rnaImmuneCellSamples = null;
        rnaImmuneCellSampleTpmMs = null;
        rnaImmuneCellMonacos = null;
        rnaImmuneCellSchmiedels = null;
        rnaCellineCancers = null;
        rnaCellines = null;
        rnaCellineDescriptions = null;
        rnaCancerSamples = null;
        // delete?
        transcriptRnaTissues = null;
        transcriptRnaBrains = null;
        transcriptRnaGtexretinas = null;
        transcriptRnaBloodcells = null;
        transcriptRnaPigbrains = null;
        transcriptRnaMousebrains = null;
        proteinatlas = null;
    }
}
