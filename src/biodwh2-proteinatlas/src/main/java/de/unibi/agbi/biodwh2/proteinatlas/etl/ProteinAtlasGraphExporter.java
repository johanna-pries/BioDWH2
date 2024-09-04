package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.GraphExporter;
import de.unibi.agbi.biodwh2.core.exceptions.ExporterException;
import de.unibi.agbi.biodwh2.core.model.graph.Graph;
import de.unibi.agbi.biodwh2.core.model.graph.IndexDescription;
import de.unibi.agbi.biodwh2.core.model.graph.Node;
import de.unibi.agbi.biodwh2.core.model.graph.NodeBuilder;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;
import de.unibi.agbi.biodwh2.proteinatlas.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProteinAtlasGraphExporter extends GraphExporter<ProteinAtlasDataSource> {
    private static final Logger LOGGER = LogManager.getLogger(ProteinAtlasGraphExporter.class);
    private static final String ID_PROPERTY = "id";
    private static final String GENE_LABEL = "gene";
    private static final String TISSUE_LABEL = "tissue";
    private static final String CELL_TYPE_LABEL = "cellType";
    private static final String EXPRESSION_DATA_LABEL = "expressionData";
    private static final String EXPRESSION_LEVELS_LABEL = "expressionLevels";
    private static final String PROGNOSTIC_DATA_LABEL = "prognosticData";
    private static final String CANCER_LABEL = "cancer";
    private static final String EXPRESSION_METRICS_LABEL = "expressionMetrics";
    private static final String BRAIN_REGION_LABEL = "brainRegion";

    private static final String NORMAL_TISSUES_LABEL = "NormalTissues";
    private static final String PATHOLOGIES_LABEL = "Pathologies";
    private static final String RNA_BRAIN_FANTOMS_LABEL = "RnaBrainFantoms";
    private static final String RNA_BRAIN_GTEXES_LABEL = "RnaBrainGtexes";

    public ProteinAtlasGraphExporter(final ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public long getExportVersion() {
        return 1;
    }

    @Override
    protected boolean exportGraph(final Workspace workspace, final Graph graph) throws ExporterException {
        // WIP
        graph.addIndex(IndexDescription.forNode(NORMAL_TISSUES_LABEL, ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(PATHOLOGIES_LABEL, ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(RNA_BRAIN_FANTOMS_LABEL, ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(RNA_BRAIN_GTEXES_LABEL, ID_PROPERTY, IndexDescription.Type.UNIQUE));
        /*
        gra0ph.addIndex(IndexDescription.forNode("RnaBrainHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaCancerSamples", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaCellines", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaCellineCancers", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaCellineDescriptions", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaImmuneCells", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaImmuneCellMonacos", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaImmuneCellSamples", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaImmuneCellSampleTpmMs", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaImmuneCellSchmiedels", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaMouseBrainAllens", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaMouseBrainHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaMouseBrainMouseSamples", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaMouseBrainSampleHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaPfcBrainHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaPigBrainHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaPigBrainSampleHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaSingleCellClusterDescriptions", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaSingleCellReadCounts", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaSingleCellTypes", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaSingleCellTypeTissues", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaTissueConsensuses", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaTissueFantoms", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaTissueGtexes", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaTissueHpas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("RnaTissueHpaDescription", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("SubcellularLocations", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaBloodcells", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaBrains", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaGtexRetinas", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaMouseBrains", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaPigBrains", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode("TranscriptRnaTissues", ID_PROPERTY, IndexDescription.Type.UNIQUE));
        */

        addNormalTissues(graph, dataSource.normalTissues);
        addPathologies(graph, dataSource.pathologies);
        addRnaBrainFantoms(graph, dataSource.rnaBrainFantoms);
        addRnaBrainGtexes(graph, dataSource.rnaBrainGtexes);
        /*
        addRnaBrainHpas(graph, dataSource.rnaBrainHpas);
        addRnaCancerSamples(graph, dataSource.rnaCancerSamples);
        addRnaCellines(graph, dataSource.rnaCellines);
        addRnaCellineCancers(graph, dataSource.rnaCellineCancers);
        addRnaCellineDescriptions(graph, dataSource.rnaCellineDescriptions);
        addRnaImmuneCells(graph, dataSource.rnaImmuneCells);
        addRnaImmuneCellMonacos(graph, dataSource.rnaImmuneCellMonacos);
        addRnaImmuneCellSamples(graph, dataSource.rnaImmuneCellSamples);
        addRnaImmuneCellSampleTpmMs(graph, dataSource.rnaImmuneCellSampleTpmMs);
        addRnaImmuneCellSchmiedels(graph, dataSource.rnaImmuneCellSchmiedels);
        addRnaMouseBrainAllens(graph, dataSource.rnaMouseBrainAllens);
        addRnaMouseBrainHpas(graph, dataSource.rnaMouseBrainHpas);
        addRnaMouseBrainMouseSamples(graph, dataSource.rnaMouseBrainMouseSamples);
        addRnaMouseBrainSampleHpas(graph, dataSource.rnaMouseBrainSampleHpas);
        addRnaPfcBrainHpas(graph, dataSource.rnaPfcBrainHpas);
        addRnaPigBrainHpas(graph, dataSource.rnaPigBrainHpas);
        addRnaPigBrainSampleHpas(graph, dataSource.rnaPigBrainSampleHpas);
        addRnaSingleCellClusterDescriptions(graph, dataSource.rnaSingleCellClusterDescriptions);
        addRnaSingleCellReadCounts(graph, dataSource.rnaSingleCellReadCounts);
        addRnaSingleCellTypes(graph, dataSource.rnaSingleCellTypes);
        addRnaSingleCellTypeTissues(graph, dataSource.rnaSingleCellTypeTissues);
        addRnaTissueConsensuses(graph, dataSource.rnaTissueConsensuses);
        addRnaTissueFantoms(graph, dataSource.rnaTissueFantoms);
        addRnaTissueGtexes(graph, dataSource.rnaTissueGtexes);
        addRnaTissueHpas(graph, dataSource.rnaTissueHpas);
        addRnaTissueHpaDescription(graph, dataSource.rnaTissueHpaDescriptions);
        addSubcellularLocations(graph, dataSource.subcellularLocations);
        addTranscriptRnaBloodcells(graph, dataSource.transcriptRnaBloodcells);
        addTranscriptRnaBrains(graph, dataSource.transcriptRnaBrains);
        addTranscriptRnaGtexRetinas(graph, dataSource.transcriptRnaGtexretinas);
        addTranscriptRnaMouseBrains(graph, dataSource.transcriptRnaMousebrains);
        addTranscriptRnaPigBrains(graph, dataSource.transcriptRnaPigbrains);
        addTranscriptRnaTissues(graph, dataSource.transcriptRnaTissues);
        */

        return true;
    }

    private void addNormalTissues(final Graph graph, final List<NormalTissue> normalTissues) {
        LOGGER.info("Add NormalTissues...");
        for (final NormalTissue normalTissue : normalTissues) {
            addNormalTissue(graph, normalTissue);
        }
    }

    private void addNormalTissue(final Graph graph, final NormalTissue normalTissue) {
        final Node tissueNode = getOrCreateTissueNode(graph, normalTissue.tissue);
        final Node geneNode = getOrCreateGeneNode(graph, normalTissue.gene, normalTissue.geneName);
        final Node cellTypeNode = getOrCreateCellTypeNode(graph, normalTissue.cellType);
        final Node expressionDataNode = graph.addNode(EXPRESSION_DATA_LABEL, "level", normalTissue.level, "reliability", normalTissue.reliability);

        graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
        graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
        graph.addEdge(tissueNode, cellTypeNode, "CONTAINS");
        graph.addEdge(cellTypeNode, tissueNode, "CONTAINED_IN");
        graph.addEdge(geneNode, expressionDataNode, "ASSOCIATED_WITH");
        graph.addEdge(tissueNode, expressionDataNode, "ASSOCIATED_WITH");
        graph.addEdge(cellTypeNode, expressionDataNode, "ASSOCIATED_WITH");
    }

    private Node getOrCreateTissueNode(final Graph graph, final String tissueName) {
        Node node = graph.findNode(TISSUE_LABEL, tissueName);
        if (node == null)
            node = graph.addNode(TISSUE_LABEL, "name", tissueName);
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId, final String geneName) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node == null)
            node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName);
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node == null)
            node = graph.addNode(GENE_LABEL, ID_KEY, geneId);
        return node;
    }

    private Node getOrCreateCellTypeNode(final Graph graph, final String cellTypeName) {
        Node node = graph.findNode(CELL_TYPE_LABEL, cellTypeName);
        if (node == null)
            node = graph.addNode(CELL_TYPE_LABEL, "name", cellTypeName);
        return node;
    }


    private void addPathologies(final Graph graph, final List<Pathology> pathologies) {
        LOGGER.info("Add Pathologies...");
        for (final Pathology pathology : pathologies) {
            addPathology(graph, pathology);
        }
    }

    private void addPathology(final Graph graph, final Pathology pathology) {
        final Node geneNode = getOrCreateGeneNode(graph, pathology.gene, pathology.geneName);
        final Node cancerNode = getOrCreateCancerNode(graph, pathology.cancer);
        final Node expressionLevelsNode = graph.addNode(EXPRESSION_LEVELS_LABEL, "high", pathology.high, "medium", pathology.medium, "low", pathology.low, "notDetected", pathology.notDetected);
        final Node prognosticDataNode = graph.addNode(PROGNOSTIC_DATA_LABEL, "prognosticFavorable", pathology.prognosticFavorable, "unprognosticFavorable", pathology.unprognosticFavorable, "prognosticUnfavorable", pathology.prognosticUnfavorable, "unprognosticUnfavorable", pathology.unprognosticUnfavorable);

        graph.addEdge(geneNode, cancerNode, "ASSOCIATED_WITH");
        graph.addEdge(cancerNode, geneNode, "ASSOCIATED_WITH");
        graph.addEdge(geneNode, expressionLevelsNode, "HAS_EXPRESSIONS_LEVELS");
        graph.addEdge(cancerNode, expressionLevelsNode, "HAS_EXPRESSIONS_LEVELS");
        graph.addEdge(geneNode, prognosticDataNode, "HAS_PROGNOSTIC_DATA");
        graph.addEdge(cancerNode, prognosticDataNode, "HAS_PROGNOSTIC_DATA");
    }

    private Node getOrCreateCancerNode(final Graph graph, final String cancerType) {
        Node node = graph.findNode(CANCER_LABEL, cancerType);
        if (node == null)
            node = graph.addNode(CANCER_LABEL, "type", cancerType);
        return node;
    }


    private void addRnaBrainFantoms(final Graph graph, final List<RnaBrainFantom> rnaBrainFantoms) {
        LOGGER.info("Add RnaBrainFantoms...");
        for (final RnaBrainFantom rnaBrainFantom: rnaBrainFantoms) {
            addRnaBrainFantom(graph, rnaBrainFantom);
        }
    }

    private void addRnaBrainFantom(final Graph graph, final RnaBrainFantom rnaBrainFantom) {
        final Node geneNode = getOrCreateGeneNode(graph, rnaBrainFantom.gene, rnaBrainFantom.geneName);
        final Node brainRegionNode = getOrCreateBrainRegionNode(graph, rnaBrainFantom.brainRegion);
        final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainFantom.tagsPerMillion, rnaBrainFantom.scaledTagsPerMillion, null, rnaBrainFantom.nTpm);

        graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
        graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
        graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
    }

    private Node getOrCreateBrainRegionNode(final Graph graph, final String brainRegion) {
        Node node = graph.findNode(BRAIN_REGION_LABEL, brainRegion);
        if (node == null)
            node = graph.addNode(BRAIN_REGION_LABEL, "name", brainRegion);
        return node;
    }

    private Node createExpressionMetricsNode(final Graph graph, final Float tpm, final Float scaledTpm, final Float pTpm, final Float nTpm) {
        final NodeBuilder builder = graph.buildNode().withLabel(EXPRESSION_METRICS_LABEL);
        builder.withPropertyIfNotNull("tpm", tpm);
        builder.withPropertyIfNotNull("scaledTpm", scaledTpm);
        builder.withPropertyIfNotNull("pTpm", pTpm);
        builder.withPropertyIfNotNull("nTpm", nTpm);
        return builder.build();
    }


    private void addRnaBrainGtexes(final Graph graph, final List<RnaBrainGtex> rnaBrainGtexes) {
        LOGGER.info("Add RnaBrainGtexes...");
        for (final RnaBrainGtex rnaBrainGtex: rnaBrainGtexes) {
            addRnaBrainGtex(graph, rnaBrainGtex);
        }
    }

    private void addRnaBrainGtex(final Graph graph, final RnaBrainGtex rnaBrainGtex) {
        final Node geneNode = getOrCreateGeneNode(graph, rnaBrainGtex.gene, rnaBrainGtex.geneName);
        final Node brainRegionNode = getOrCreateBrainRegionNode(graph, rnaBrainGtex.brainRegion);
        final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainGtex.tpm, null, rnaBrainGtex.pTpm, rnaBrainGtex.nTpm);

        graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
        graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
        graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
    }


    private void addRnaBrainHpas(final Graph graph, final List<RnaBrainHpa> rnaBrainHpas) {

    }

    private void addRnaCancerSamples(final Graph graph, final List<RnaCancerSample> rnaCancerSamples) {

    }

    private void addRnaCellines(final Graph graph, final List<RnaCelline> rnaCellines) {

    }

    private void addRnaCellineCancers(final Graph graph, final List<RnaCellineCancer> rnaCellineCancers) {

    }

    private void addRnaCellineDescriptions(final Graph graph, final List<RnaCellineDescription> rnaCellineDescriptions) {

    }

    private void addRnaImmuneCells(final Graph graph, final List<RnaImmuneCell> rnaImmuneCells) {

    }

    private void addRnaImmuneCellMonacos(final Graph graph, final List<RnaImmuneCellMonaco> rnaImmuneCellMonacos) {

    }

    private void addRnaImmuneCellSamples(final Graph graph, final List<RnaImmuneCellSample> rnaImmuneCellSamples) {

    }

    private void addRnaImmuneCellSampleTpmMs(final Graph graph, final List<RnaImmuneCellSampleTpmM> rnaImmuneCellSampleTpmMs) {

    }

    private void addRnaImmuneCellSchmiedels(final Graph graph, final List<RnaImmuneCellSchmiedel> rnaImmuneCellSchmiedels) {

    }

    private void addRnaMouseBrainAllens(final Graph graph, final List<RnaMouseBrainAllen> rnaMouseBrainAllens) {

    }

    private void addRnaMouseBrainHpas(final Graph graph, final List<RnaMouseBrainHpa> rnaMouseBrainHpas) {

    }

    private void addRnaMouseBrainMouseSamples(final Graph graph, final List<RnaMouseBrainMouseSample> rnaMouseBrainMouseSamples) {

    }

    private void addRnaMouseBrainSampleHpas(final Graph graph, final List<RnaMouseBrainSampleHpa> rnaMouseBrainSampleHpas) {

    }

    private void addRnaPfcBrainHpas(final Graph graph, final List<RnaPfcBrainHpa> rnaPfcBrainHpas) {

    }

    private void addRnaPigBrainHpas(final Graph graph, final List<RnaPigBrainHpa> rnaPigBrainHpas) {

    }

    private void addRnaPigBrainSampleHpas(final Graph graph, final List<RnaPigBrainSampleHpa> rnaPigBrainSampleHpas) {

    }

    private void addRnaSingleCellClusterDescriptions(final Graph graph,
                                                     final List<RnaSingleCellClusterDescription> rnaSingleCellClusterDescriptions) {

    }

    private void addRnaSingleCellReadCounts(final Graph graph, final List<RnaSingleCellReadCount> rnaSingleCellReadCounts) {

    }

    private void addRnaSingleCellTypes(final Graph graph, final List<RnaSingleCellType> rnaSingleCellTypes) {

    }

    private void addRnaSingleCellTypeTissues(final Graph graph, final List<RnaSingleCellTypeTissue> rnaSingleCellTypeTissues) {

    }

    private void addRnaTissueConsensuses(final Graph graph, final List<RnaTissueConsensus> rnaTissueConsensuses) {

    }

    private void addRnaTissueFantoms(final Graph graph, final List<RnaTissueFantom> rnaTissueFantoms) {

    }

    private void addRnaTissueGtexes(final Graph graph, final List<RnaTissueGtex> rnaTissueGtexes) {

    }

    private void addRnaTissueHpas(final Graph graph, final List<RnaTissueHpa> rnaTissueHpas) {

    }

    private void addRnaTissueHpaDescription(final Graph graph, final List<RnaTissueHpaDescription> rnaTissueHpaDescriptions) {

    }

    private void addSubcellularLocations(final Graph graph, final List<SubcellularLocation> subcellularLocations) {

    }

    private void addTranscriptRnaBloodcells(final Graph graph, final List<TranscriptRnaBloodcells> transcriptRnaBloodcells) {

    }

    private void addTranscriptRnaBrains(final Graph graph, final List<TranscriptRnaBrain> transcriptRnaBrains) {

    }

    private void addTranscriptRnaGtexRetinas(final Graph graph, final List<TranscriptRnaGtexRetina> transcriptRnaGtexretinas) {

    }

    private void addTranscriptRnaMouseBrains(final Graph graph, final List<TranscriptRnaMouseBrain> transcriptRnaMousebrains) {

    }

    private void addTranscriptRnaPigBrains(final Graph graph, final List<TranscriptRnaPigBrain> transcriptRnaPigbrains) {

    }

    private void addTranscriptRnaTissues(final Graph graph, final List<TranscriptRnaTissue> transcriptRnaTissues) {

    }
}
