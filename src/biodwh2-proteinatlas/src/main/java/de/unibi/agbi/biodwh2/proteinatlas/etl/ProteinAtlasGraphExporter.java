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
import java.util.Objects;

public class ProteinAtlasGraphExporter extends GraphExporter<ProteinAtlasDataSource> {
    private static final Logger LOGGER = LogManager.getLogger(ProteinAtlasGraphExporter.class);
    private static final String GENE_LABEL = "gene";
    private static final String TISSUE_LABEL = "tissue";
    private static final String CELL_TYPE_LABEL = "cellType";
    private static final String EXPRESSION_DATA_LABEL = "expressionData";
    private static final String EXPRESSION_LEVELS_LABEL = "expressionLevels";
    private static final String PROGNOSTIC_DATA_LABEL = "prognosticData";
    private static final String CANCER_LABEL = "cancer";
    private static final String EXPRESSION_METRICS_LABEL = "expressionMetrics";
    private static final String BRAIN_REGION_LABEL = "brainRegion";
    private static final String SUBREGION_LABEL = "subregion";
    private static final String SAMPLE_LABEL = "sample";
    private static final String CELL_LINE_LABEL = "cellLine";
    private static final String IMMUNE_CELL_LABEL = "immuneCell";
    private static final String DONOR_LABEL = "donor";

    public ProteinAtlasGraphExporter(final ProteinAtlasDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public long getExportVersion() {
        return 1;
    }

    @Override
    protected boolean exportGraph(final Workspace workspace, final Graph graph) throws ExporterException {
        graph.addIndex(IndexDescription.forNode(GENE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(TISSUE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(CELL_TYPE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(EXPRESSION_DATA_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(EXPRESSION_DATA_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(EXPRESSION_LEVELS_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(PROGNOSTIC_DATA_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(CANCER_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(EXPRESSION_METRICS_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(BRAIN_REGION_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(SUBREGION_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(SAMPLE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(CELL_LINE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(IMMUNE_CELL_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(DONOR_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        // TODO: Add indices for nodes in remaining files here.

        addNormalTissues(graph, dataSource.normalTissues);
        addPathologies(graph, dataSource.pathologies);
        addRnaBrainFantoms(graph, dataSource.rnaBrainFantoms);
        addRnaBrainGtexes(graph, dataSource.rnaBrainGtexes);
        addRnaBrainHpas(graph, dataSource.rnaBrainHpas);
        addRnaCancerSamples(graph, dataSource.rnaCancerSamples);
        addRnaCellines(graph, dataSource.rnaCellines);
        addRnaCellineCancers(graph, dataSource.rnaCellineCancers);
        addRnaCellineDescriptions(graph, dataSource.rnaCellineDescriptions);
        addRnaImmuneCells(graph, dataSource.rnaImmuneCells);
        addRnaImmuneCellMonacos(graph, dataSource.rnaImmuneCellMonacos);
        addRnaImmuneCellSamples(graph, dataSource.rnaImmuneCellSamples);
        // TODO: addRnaImmuneCellSampleTpmMs(graph, dataSource.rnaImmuneCellSampleTpmMs);
        addRnaImmuneCellSchmiedels(graph, dataSource.rnaImmuneCellSchmiedels);
        /*
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for creating specific nodes. ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Node getOrCreateNode(final Graph graph, final String label, final String propertyKey, final String value) {
        Node node = graph.findNode(label, value);
        if (node == null)
            node = graph.addNode(label, propertyKey, value);
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId, final String geneName) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node != null && !node.hasProperty("name")) {
            node.setProperty("name", geneName);
            graph.update(node);
        }
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

    private Node createExpressionMetricsNode(final Graph graph, final Float tpm, final Float scaledTpm,
                                             final Float pTpm, final Float nTpm, final Float fpkm) {
        final NodeBuilder builder = graph.buildNode().withLabel(EXPRESSION_METRICS_LABEL);
        builder.withPropertyIfNotNull("tpm", tpm);
        builder.withPropertyIfNotNull("scaledTpm", scaledTpm);
        builder.withPropertyIfNotNull("pTpm", pTpm);
        builder.withPropertyIfNotNull("nTpm", nTpm);
        builder.withPropertyIfNotNull("fpkm", fpkm);
        return builder.build();
    }

    private Node getOrCreateCellLineNode(final Graph graph, final String id, final String cellosaurusId,
                                         final String diseaseType, final String diseaseSubtype,
                                         final String patientData, final String primaryOrMetastasis,
                                         final String sampleCollectionSite) {
        Node node = graph.findNode(CELL_LINE_LABEL, ID_KEY, id);
        if (node != null) {
            node.setProperty("cellosaurusId", cellosaurusId);
            node.setProperty("diseaseType", diseaseType);
            node.setProperty("diseaseSubtype", diseaseSubtype);
            // TODO: Split into two separate fields for age and gender.
            node.setProperty("patientData", patientData);
            if (Objects.equals(primaryOrMetastasis, "primary")) {
                node.setProperty("primary", true);
                node.setProperty("metastasis", false);
            } else if (Objects.equals(primaryOrMetastasis, "metastasis")) {
                node.setProperty("primary", false);
                node.setProperty("metastasis", true);
            } else {
                node.setProperty("primary/metastasis", primaryOrMetastasis);
            }
            node.setProperty("collectionSite", sampleCollectionSite);
            graph.update(node);
        }
        if (node == null) {
            final NodeBuilder builder = graph.buildNode().withLabel(CELL_LINE_LABEL);
            builder.withPropertyIfNotNull(ID_KEY, id);
            builder.withPropertyIfNotNull("cellosaurusId", cellosaurusId);
            builder.withPropertyIfNotNull("diseaseType", diseaseType);
            builder.withPropertyIfNotNull("diseaseSubtype", diseaseSubtype);
            // TODO: Split into two separate fields for age and gender.
            builder.withPropertyIfNotNull("patientData", patientData);
            if (Objects.equals(primaryOrMetastasis, "primary")) {
                builder.withPropertyIfNotNull("primary", true);
                builder.withPropertyIfNotNull("metastasis", false);
            } else if (Objects.equals(primaryOrMetastasis, "metastasis")) {
                builder.withPropertyIfNotNull("primary", false);
                builder.withPropertyIfNotNull("metastasis", true);
            } else {
                builder.withPropertyIfNotNull("primary/metastasis", primaryOrMetastasis);
            }
            builder.withPropertyIfNotNull("collectionSite", sampleCollectionSite);
            node = builder.build();
        }
        return node;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for adding the parsed files. ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addNormalTissues(final Graph graph, final List<NormalTissue> normalTissues) {
        LOGGER.info("Add NormalTissues...");
        for (final NormalTissue normalTissue : normalTissues) {

            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", normalTissue.tissue);
            final Node geneNode = getOrCreateGeneNode(graph, normalTissue.gene, normalTissue.geneName);
            final Node cellTypeNode = getOrCreateNode(graph, CELL_TYPE_LABEL, "name", normalTissue.cellType);
            final Node expressionDataNode = graph.addNode(EXPRESSION_DATA_LABEL, "level", normalTissue.level,
                                                          "reliability", normalTissue.reliability);

            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(tissueNode, cellTypeNode, "CONTAINS");
            graph.addEdge(cellTypeNode, tissueNode, "CONTAINED_IN");
            graph.addEdge(geneNode, expressionDataNode, "ASSOCIATED_WITH");
            graph.addEdge(tissueNode, expressionDataNode, "ASSOCIATED_WITH");
            graph.addEdge(cellTypeNode, expressionDataNode, "ASSOCIATED_WITH");
        }
    }

    private void addPathologies(final Graph graph, final List<Pathology> pathologies) {
        LOGGER.info("Add Pathologies...");
        for (final Pathology pathology : pathologies) {

            final Node geneNode = getOrCreateGeneNode(graph, pathology.gene, pathology.geneName);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", pathology.cancer);
            final Node expressionLevelsNode = graph.addNode(EXPRESSION_LEVELS_LABEL, "high", pathology.high,
                                                            "medium", pathology.medium, "low",
                                                            pathology.low, "notDetected", pathology.notDetected);
            final Node prognosticDataNode = graph.addNode(PROGNOSTIC_DATA_LABEL, "prognosticFavorable",
                                                          pathology.prognosticFavorable, "unprognosticFavorable",
                                                          pathology.unprognosticFavorable, "prognosticUnfavorable",
                                                          pathology.prognosticUnfavorable, "unprognosticUnfavorable",
                                                          pathology.unprognosticUnfavorable);

            graph.addEdge(geneNode, cancerNode, "ASSOCIATED_WITH");
            graph.addEdge(cancerNode, geneNode, "ASSOCIATED_WITH");
            graph.addEdge(geneNode, expressionLevelsNode, "HAS_EXPRESSIONS_LEVELS");
            graph.addEdge(cancerNode, expressionLevelsNode, "HAS_EXPRESSIONS_LEVELS");
            graph.addEdge(geneNode, prognosticDataNode, "HAS_PROGNOSTIC_DATA");
            graph.addEdge(cancerNode, prognosticDataNode, "HAS_PROGNOSTIC_DATA");
        }
    }

    private void addRnaBrainFantoms(final Graph graph, final List<RnaBrainFantom> rnaBrainFantoms) {
        LOGGER.info("Add RnaBrainFantoms...");
        for (final RnaBrainFantom rnaBrainFantom: rnaBrainFantoms) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainFantom.gene, rnaBrainFantom.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                         rnaBrainFantom.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainFantom.tagsPerMillion,
                                                                           rnaBrainFantom.scaledTagsPerMillion, null,
                                                                           rnaBrainFantom.nTpm, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaBrainGtexes(final Graph graph, final List<RnaBrainGtex> rnaBrainGtexes) {
        LOGGER.info("Add RnaBrainGtexes...");
        for (final RnaBrainGtex rnaBrainGtex: rnaBrainGtexes) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainGtex.gene, rnaBrainGtex.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                         rnaBrainGtex.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainGtex.tpm, null,
                                                                           rnaBrainGtex.pTpm, rnaBrainGtex.nTpm, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaBrainHpas(final Graph graph, final List<RnaBrainHpa> rnaBrainHpas) {
        LOGGER.info("Add RnaBrainHpas...");
        for (final RnaBrainHpa rnaBrainHpa: rnaBrainHpas) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainHpa.gene, rnaBrainHpa.geneName);
            final Node subregionNode = getOrCreateNode(graph, SUBREGION_LABEL, "name", rnaBrainHpa.subregion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainHpa.tpm, null,
                                                                           rnaBrainHpa.pTpm, rnaBrainHpa.nTpm, null);

            graph.addEdge(geneNode, subregionNode, "EXPRESSED_IN");
            graph.addEdge(subregionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(subregionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCancerSamples(final Graph graph, final List<RnaCancerSample> rnaCancerSamples) {
        LOGGER.info("Add RnaCancerSamples...");
        for (final RnaCancerSample rnaCancerSample: rnaCancerSamples) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCancerSample.gene);
            final Node sampleNode = getOrCreateNode(graph, SAMPLE_LABEL, "id", rnaCancerSample.sample);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", rnaCancerSample.cancer);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null,
                                                                           null, rnaCancerSample.fpkm);

            graph.addEdge(geneNode, sampleNode, "EXPRESSED_IN");
            graph.addEdge(sampleNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(sampleNode, cancerNode, "HAS_CANCER_TYPE");
            graph.addEdge(sampleNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellines(final Graph graph, final List<RnaCelline> rnaCellines) {
        LOGGER.info("Add RnaCellines...");
        for (final RnaCelline rnaCelline: rnaCellines) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCelline.gene, rnaCelline.geneName);
            final Node cellLineNode = getOrCreateCellLineNode(graph, rnaCelline.cellLine, null, null,
                                                              null, null, null,
                                                              null);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaCelline.tpm, null,
                                                                           rnaCelline.pTpm, rnaCelline.nTpm, null);

            graph.addEdge(geneNode, cellLineNode, "EXPRESSED_IN");
            graph.addEdge(cellLineNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cellLineNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellineCancers(final Graph graph, final List<RnaCellineCancer> rnaCellineCancers) {
        LOGGER.info("Add RnaCellineCancers...");
        for (final RnaCellineCancer rnaCellineCancer: rnaCellineCancers) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCellineCancer.gene);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", rnaCellineCancer.cancer);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaCellineCancer.tpm, null,
                                                                           rnaCellineCancer.pTpm, rnaCellineCancer.nTpm,
                                                                           null);

            graph.addEdge(geneNode, cancerNode, "EXPRESSED_IN");
            graph.addEdge(cancerNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cancerNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellineDescriptions(final Graph graph, final List<RnaCellineDescription> rnaCellineDescriptions) {
        LOGGER.info("Add RnaCellineDescriptions...");
        for (final RnaCellineDescription rnaCellineDescription: rnaCellineDescriptions) {

            getOrCreateCellLineNode(graph, rnaCellineDescription.cellLine, rnaCellineDescription.cellosaurusId,
                                    rnaCellineDescription.disease, rnaCellineDescription.diseaseSubtype,
                                    rnaCellineDescription.patient, rnaCellineDescription.primaryMetastasis,
                                    rnaCellineDescription.sampleCollectionSite);
        }
    }

    private void addRnaImmuneCells(final Graph graph, final List<RnaImmuneCell> rnaImmuneCells) {
        LOGGER.info("Add RnaImmuneCells...");
        for (final RnaImmuneCell rnaImmuneCell: rnaImmuneCells) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCell.gene, rnaImmuneCell.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name", rnaImmuneCell.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCell.tpm, null,
                                                                           rnaImmuneCell.pTpm, rnaImmuneCell.nTpm, null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaImmuneCellMonacos(final Graph graph, final List<RnaImmuneCellMonaco> rnaImmuneCellMonacos) {
        LOGGER.info("Add RnaImmuneCellMonacos...");
        for (final RnaImmuneCellMonaco rnaImmuneCellMonaco: rnaImmuneCellMonacos) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellMonaco.gene, rnaImmuneCellMonaco.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellMonaco.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellMonaco.tpm, null,
                                                                           rnaImmuneCellMonaco.pTpm, null, null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaImmuneCellSamples(final Graph graph, final List<RnaImmuneCellSample> rnaImmuneCellSamples) {
        LOGGER.info("Add RnaImmuneCellSamples...");
        for (final RnaImmuneCellSample rnaImmuneCellSample: rnaImmuneCellSamples) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellSample.ensgId, rnaImmuneCellSample.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellSample.immuneCell);
            // TODO: Leave this in here or not? Not a real ID!
            // final Node sampleNode = graph.addNode(SAMPLE_LABEL, "id", rnaImmuneCellSample.sampleId);
            final Node donorNode = graph.addNode(DONOR_LABEL, "name", rnaImmuneCellSample.donor);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellSample.tpm, null,
                                                                           rnaImmuneCellSample.pTpm, rnaImmuneCellSample.nTpm,
                                                                           null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(donorNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            // TODO: Leave this in here or not?
            // graph.addEdge(sampleNode, donorNode, "SAMPLED_FROM");
            // graph.addEdge(sampleNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    // TODO: This file is not as the others. Looks like a matrix, but not all columns are labeled?
    private void addRnaImmuneCellSampleTpmMs(final Graph graph, final List<RnaImmuneCellSampleTpmM> rnaImmuneCellSampleTpmMs) {
    }

    private void addRnaImmuneCellSchmiedels(final Graph graph, final List<RnaImmuneCellSchmiedel> rnaImmuneCellSchmiedels) {
        LOGGER.info("Add RnaImmuneCellSchmiedels...");
        for (final RnaImmuneCellSchmiedel rnaImmuneCellSchmiedel: rnaImmuneCellSchmiedels) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellSchmiedel.gene, rnaImmuneCellSchmiedel.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellSchmiedel.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellSchmiedel.tpm, null,
                                                                           null, null, null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
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
