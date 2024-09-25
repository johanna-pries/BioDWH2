package de.unibi.agbi.biodwh2.proteinatlas.etl;

import com.fasterxml.jackson.databind.MappingIterator;
import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.GraphExporter;
import de.unibi.agbi.biodwh2.core.exceptions.ExporterException;
import de.unibi.agbi.biodwh2.core.exceptions.ExporterFormatException;
import de.unibi.agbi.biodwh2.core.io.FileUtils;
import de.unibi.agbi.biodwh2.core.model.graph.Graph;
import de.unibi.agbi.biodwh2.core.model.graph.IndexDescription;
import de.unibi.agbi.biodwh2.core.model.graph.Node;
import de.unibi.agbi.biodwh2.core.model.graph.NodeBuilder;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;
import de.unibi.agbi.biodwh2.proteinatlas.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipInputStream;

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
    private static final String SAMPLE_LABEL = "sample";
    private static final String CELL_LINE_LABEL = "cellLine";
    private static final String IMMUNE_CELL_LABEL = "immuneCell";
    private static final String DONOR_LABEL = "donor";
    private static final String MOUSE_DATA_LABEL = "mouseData";
    private static final String PIG_DATA_LABEL = "pigData";
    private static final String CELL_TYPE_GROUP_LABEL = "cellTypeGroup";
    private static final String CLUSTER_DATA_LABEL = "clusterData";
    private static final String LOCATION_LABEL = "location";
    private static final String CELL_METRICS_LABEL = "cellMetrics";

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
        graph.addIndex(IndexDescription.forNode(SAMPLE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(CELL_LINE_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(IMMUNE_CELL_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(DONOR_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(MOUSE_DATA_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(PIG_DATA_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(LOCATION_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        graph.addIndex(IndexDescription.forNode(CELL_METRICS_LABEL, ID_KEY, IndexDescription.Type.UNIQUE));
        // TODO: Add indices for nodes in remaining files here.

        addNormalTissues(workspace, graph);
        addPathologies(workspace, graph);
        addRnaBrainFantoms(workspace, graph);
        addRnaBrainGtexes(workspace, graph);
        addRnaBrainHpas(workspace, graph);
        addRnaCancerSamples(workspace, graph);
        addRnaCellines(workspace, graph);
        addRnaCellineCancers(workspace, graph);
        addRnaCellineDescriptions(workspace, graph);
        addRnaImmuneCells(workspace, graph);
        addRnaImmuneCellMonacos(workspace, graph);
        addRnaImmuneCellSamples(workspace, graph);
        // TODO: addRnaImmuneCellSampleTpmMs(workspace, graph);
        addRnaImmuneCellSchmiedels(workspace, graph);
        addRnaMouseBrainAllens(workspace, graph);
        addRnaMouseBrainHpas(workspace, graph);
        addRnaMouseBrainMouseSamples(workspace, graph);
        addRnaMouseBrainSampleHpas(workspace, graph);
        addRnaPfcBrainHpas(workspace, graph);
        addRnaPigBrainHpas(workspace, graph);
        addRnaPigBrainSampleHpas(workspace, graph);
        addRnaSingleCellClusterDescriptions(workspace, graph);
        addRnaSingleCellTypes(workspace, graph);
        addRnaSingleCellTypeTissues(workspace, graph);
        addRnaTissueConsensuses(workspace, graph);
        addRnaTissueFantoms(workspace, graph);
        addRnaTissueGtexes(workspace, graph);
        addRnaTissueHpas(workspace, graph);
        addRnaTissueHpaDescription(workspace, graph);
        addSubcellularLocations(workspace, graph);
        /*
        addTranscriptRnaBloodcells(workspace, graph);
        addTranscriptRnaBrains(workspace, graph);
        addTranscriptRnaGtexRetinas(workspace, graph);
        addTranscriptRnaMouseBrains(workspace, graph);
        addTranscriptRnaPigBrains(workspace, graph);
        addTranscriptRnaTissues(workspace, graph);
        */
        return true;
    }

    /*
     * Method for parsing TSV-files which are zipped.
     */
    private <T> Iterable<T> parseZipTsvFile(final Workspace workspace, final String fileName, final Class<T> typeClass) throws ExporterException {
        try {
            final ZipInputStream stream = FileUtils.openZip(workspace, dataSource, fileName);
            // TODO: Are the headers skipped because of this? But without it, it does not work.
            stream.getNextEntry();
            final MappingIterator<T> iterator = FileUtils.openSeparatedValuesFile(stream, typeClass, '\t', false);
            return () -> iterator;
        } catch (IOException e) {
            throw new ExporterFormatException("Failed to parse the file '" + fileName + "'", e);
        }
    }

    /*
     * Method for parsing TSV-files.
     */
    private <T> Iterable<T> parseTsvFile(final Workspace workspace, final String fileName, final Class<T> typeClass) throws ExporterException {
        try {
            final MappingIterator<T> iterator = FileUtils.openTsv(workspace, dataSource, fileName, typeClass);
            return () -> iterator;
        } catch (IOException e) {
            throw new ExporterFormatException("Failed to parse the file '" + fileName + "'", e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Methods for creating specific nodes. ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Node getOrCreateNode(final Graph graph, final String label, final String propertyKey, final String value) {
        Node node = graph.findNode(label, propertyKey, value);
        if (node == null) {
            node = graph.addNode(label, propertyKey, value);
        }
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId, final String geneName,
                                     final String reliabilityScore) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node != null) {
            if (!node.hasProperty("name")) {
                node.setProperty("name", geneName);
                graph.update(node);
            }
            if (!node.hasProperty("reliabilityScore")) {
                node.setProperty("reliabilityScore", reliabilityScore);
                graph.update(node);
            }
        } else {
            if (geneId.contains("ENSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "reliabilityScore", reliabilityScore,
                                     "species", "Homo sapiens");
            } else if (geneId.contains("ENSMUSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "reliabilityScore", reliabilityScore,
                                     "species", "Mus musculus");
            } else if (geneId.contains("ENSSSCG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "reliabilityScore", reliabilityScore,
                                     "species", "Sus scrofa");
            } else {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "reliabilityScore", reliabilityScore);
            }
        }
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId, final String geneName) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node != null && !node.hasProperty("name")) {
            node.setProperty("name", geneName);
            graph.update(node);
        }
        if (node == null) {
            if (geneId.contains("ENSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "species", "Homo sapiens");
            } else if (geneId.contains("ENSMUSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "species", "Mus musculus");
            } else if (geneId.contains("ENSSSCG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName, "species", "Sus scrofa");
            } else {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "name", geneName);
            }
        }
        return node;
    }

    private Node getOrCreateGeneNode(final Graph graph, final String geneId) {
        Node node = graph.findNode(GENE_LABEL, ID_KEY, geneId);
        if (node == null) {
            if (geneId.contains("ENSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "species", "Homo sapiens");
            } else if (geneId.contains("ENSMUSG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "species", "Mus musculus");
            } else if (geneId.contains("ENSSSCG")) {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId, "species", "Sus scrofa");
            } else {
                node = graph.addNode(GENE_LABEL, ID_KEY, geneId);
            }
        }
        return node;
    }

    private Node createExpressionMetricsNode(final Graph graph, final Float tpm, final Float scaledTpm,
                                             final Float pTpm, final Float nTpm, final Float fpkm,
                                             final Float expressionEnergy, final Long readCount,
                                             final Float tagsPerMillion, final Float scaledTagsPerMillion,
                                             final Float normalizedTagsPerMillion) {
        final NodeBuilder builder = graph.buildNode().withLabel(EXPRESSION_METRICS_LABEL);
        builder.withPropertyIfNotNull("tpm", tpm);
        builder.withPropertyIfNotNull("scaledTpm", scaledTpm);
        builder.withPropertyIfNotNull("pTpm", pTpm);
        builder.withPropertyIfNotNull("nTpm", nTpm);
        builder.withPropertyIfNotNull("fpkm", fpkm);
        builder.withPropertyIfNotNull("expressionsEnergy", expressionEnergy);
        builder.withPropertyIfNotNull("readCount", readCount);
        builder.withPropertyIfNotNull("tagsPerMillion", tagsPerMillion);
        builder.withPropertyIfNotNull("scaledTagsPerMillion", scaledTagsPerMillion);
        builder.withPropertyIfNotNull("normalizedTagsPerMillion", normalizedTagsPerMillion);
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
            //       Examples: "Female, 54", "Male", "13".
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
            //       Examples: "Female, 54", "Male", "13".
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

    private void addNormalTissues(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add NormalTissues...");
        for (NormalTissue normalTissue : parseZipTsvFile(workspace, ProteinAtlasUpdater.NORMAL_TISSUE_FILE_NAME,
                                                         NormalTissue.class)) {

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

    private void addPathologies(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add Pathologies...");
        for (final Pathology pathology : parseZipTsvFile(workspace, ProteinAtlasUpdater.PATHOLOGY_FILE_NAME,
                                                         Pathology.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, pathology.gene, pathology.geneName);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", pathology.cancer);
            final Node expressionLevelsNode = graph.addNode(EXPRESSION_LEVELS_LABEL, "high", pathology.high, "medium",
                                                            pathology.medium, "low", pathology.low, "notDetected",
                                                            pathology.notDetected);
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

    private void addRnaBrainFantoms(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaBrainFantoms...");
        for (final RnaBrainFantom rnaBrainFantom: parseZipTsvFile(workspace,
                                                                  ProteinAtlasUpdater.RNA_BRAIN_FANTOM_FILE_NAME,
                                                                  RnaBrainFantom.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainFantom.gene, rnaBrainFantom.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name", rnaBrainFantom.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainFantom.tagsPerMillion,
                                                                           rnaBrainFantom.scaledTagsPerMillion, null,
                                                                           rnaBrainFantom.nTpm, null, null, null, null,
                                                                           null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaBrainGtexes(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaBrainGtexes...");
        for (final RnaBrainGtex rnaBrainGtex: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_BRAIN_GTEX_FILE_NAME,
                                                              RnaBrainGtex.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainGtex.gene, rnaBrainGtex.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name", rnaBrainGtex.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainGtex.tpm, null,
                                                                           rnaBrainGtex.pTpm, rnaBrainGtex.nTpm, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaBrainHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaBrainHpas...");
        for (final RnaBrainHpa rnaBrainHpa: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_BRAIN_HPA_FILE_NAME,
                                                            RnaBrainHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaBrainHpa.gene, rnaBrainHpa.geneName);
            final Node subregionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name", rnaBrainHpa.subregion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaBrainHpa.tpm, null,
                                                                           rnaBrainHpa.pTpm, rnaBrainHpa.nTpm, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, subregionNode, "EXPRESSED_IN");
            graph.addEdge(subregionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(subregionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCancerSamples(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaCancerSamples...");
        for (final RnaCancerSample rnaCancerSample: parseZipTsvFile(workspace,
                                                                    ProteinAtlasUpdater.RNA_CANCER_SAMPLE_FILE_NAME,
                                                                    RnaCancerSample.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCancerSample.gene);
            final Node sampleNode = getOrCreateNode(graph, SAMPLE_LABEL, "id", rnaCancerSample.sample);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", rnaCancerSample.cancer);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null, null,
                                                                           rnaCancerSample.fpkm, null, null, null, null,
                                                                           null);

            graph.addEdge(geneNode, sampleNode, "EXPRESSED_IN");
            graph.addEdge(sampleNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(sampleNode, cancerNode, "HAS_CANCER_TYPE");
            graph.addEdge(sampleNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellines(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaCellines...");
        for (final RnaCelline rnaCelline: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_CELLINE_FILE_NAME,
                                                          RnaCelline.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCelline.gene, rnaCelline.geneName);
            final Node cellLineNode = getOrCreateCellLineNode(graph, rnaCelline.cellLine, null, null, null, null, null,
                                                              null);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaCelline.tpm, null, rnaCelline.pTpm,
                                                                           rnaCelline.nTpm, null, null, null, null,
                                                                           null, null);

            graph.addEdge(geneNode, cellLineNode, "EXPRESSED_IN");
            graph.addEdge(cellLineNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cellLineNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellineCancers(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaCellineCancers...");
        for (final RnaCellineCancer rnaCellineCancer: parseZipTsvFile(workspace,
                                                                      ProteinAtlasUpdater.RNA_CELLINE_CANCER_FILE_NAME,
                                                                      RnaCellineCancer.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaCellineCancer.gene);
            final Node cancerNode = getOrCreateNode(graph, CANCER_LABEL, "type", rnaCellineCancer.cancer);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaCellineCancer.tpm, null,
                                                                           rnaCellineCancer.pTpm, rnaCellineCancer.nTpm,
                                                                           null, null, null, null, null, null);

            graph.addEdge(geneNode, cancerNode, "EXPRESSED_IN");
            graph.addEdge(cancerNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cancerNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaCellineDescriptions(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaCellineDescriptions...");
        for (final RnaCellineDescription rnaCellineDescription: parseZipTsvFile(workspace,
                                                                                ProteinAtlasUpdater.RNA_CELLINE_DESCRIPTION_FILE_NAME,
                                                                                RnaCellineDescription.class)) {

            getOrCreateCellLineNode(graph, rnaCellineDescription.cellLine, rnaCellineDescription.cellosaurusId,
                                    rnaCellineDescription.disease, rnaCellineDescription.diseaseSubtype,
                                    rnaCellineDescription.patient, rnaCellineDescription.primaryMetastasis,
                                    rnaCellineDescription.sampleCollectionSite);
        }
    }

    private void addRnaImmuneCells(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaImmuneCells...");
        for (final RnaImmuneCell rnaImmuneCell: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_IMMUNE_CELL_FILE_NAME,
                                                                RnaImmuneCell.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCell.gene, rnaImmuneCell.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name", rnaImmuneCell.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCell.tpm, null,
                                                                           rnaImmuneCell.pTpm, rnaImmuneCell.nTpm,
                                                                           null, null, null, null, null, null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaImmuneCellMonacos(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaImmuneCellMonacos...");
        for (final RnaImmuneCellMonaco rnaImmuneCellMonaco: parseZipTsvFile(workspace,
                                                                            ProteinAtlasUpdater.RNA_IMMUNE_CELL_MONACO_FILE_NAME,
                                                                            RnaImmuneCellMonaco.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellMonaco.gene, rnaImmuneCellMonaco.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellMonaco.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellMonaco.tpm, null,
                                                                           rnaImmuneCellMonaco.pTpm, null, null, null,
                                                                           null, null, null, null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaImmuneCellSamples(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaImmuneCellSamples...");
        for (final RnaImmuneCellSample rnaImmuneCellSample: parseZipTsvFile(workspace,
                                                                            ProteinAtlasUpdater.RNA_IMMUNE_CELL_SAMPLE_FILE_NAME,
                                                                            RnaImmuneCellSample.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellSample.ensgId, rnaImmuneCellSample.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellSample.immuneCell);
            // TODO: Leave this in here or not? Not a real ID!
            // final Node sampleNode = graph.addNode(SAMPLE_LABEL, "id", rnaImmuneCellSample.sampleId);
            final Node donorNode = graph.addNode(DONOR_LABEL, "name", rnaImmuneCellSample.donor);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellSample.tpm, null,
                                                                           rnaImmuneCellSample.pTpm,
                                                                           rnaImmuneCellSample.nTpm, null, null, null,
                                                                           null, null, null);

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

    // TODO: I think this data file is damaged. There are not enough column headers for the values.
    private void addRnaImmuneCellSampleTpmMs(final Workspace workspace, final Graph graph) {
    }

    private void addRnaImmuneCellSchmiedels(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaImmuneCellSchmiedels...");
        for (final RnaImmuneCellSchmiedel rnaImmuneCellSchmiedel: parseZipTsvFile(workspace,
                                                                                  ProteinAtlasUpdater.RNA_IMMUNE_CELL_SCHMIEDEL_FILE_NAME,
                                                                                  RnaImmuneCellSchmiedel.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaImmuneCellSchmiedel.gene, rnaImmuneCellSchmiedel.geneName);
            final Node immuneCellNode = getOrCreateNode(graph, IMMUNE_CELL_LABEL, "name",
                                                        rnaImmuneCellSchmiedel.immuneCell);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaImmuneCellSchmiedel.tpm, null,
                                                                           null, null, null, null, null, null, null,
                                                                           null);

            graph.addEdge(geneNode, immuneCellNode, "EXPRESSED_IN");
            graph.addEdge(immuneCellNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(immuneCellNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaMouseBrainAllens(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaMouseBrainAllens...");
        for (final RnaMouseBrainAllen rnaMouseBrainAllen: parseZipTsvFile(workspace,
                                                                          ProteinAtlasUpdater.RNA_MOUSE_BRAIN_ALLEN_FILE_NAME,
                                                                          RnaMouseBrainAllen.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaMouseBrainAllen.gene, rnaMouseBrainAllen.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                         rnaMouseBrainAllen.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null, null, null,
                                                                           rnaMouseBrainAllen.expressionEnergy, null,
                                                                           null, null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaMouseBrainHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaMouseBrainHpas...");
        for (final RnaMouseBrainHpa rnaMouseBrainHpa : parseZipTsvFile(workspace,
                                                                       ProteinAtlasUpdater.RNA_MOUSE_BRAIN_HPA_FILE_NAME,
                                                                       RnaMouseBrainHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaMouseBrainHpa.gene, rnaMouseBrainHpa.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                         rnaMouseBrainHpa.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaMouseBrainHpa.tpm, null,
                                                                           rnaMouseBrainHpa.pTpm, rnaMouseBrainHpa.nTpm,
                                                                           null, null, null, null, null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaMouseBrainMouseSamples(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaMouseBrainMouseSamples...");
        // TODO: check if the parsing works like this.
        for (final RnaMouseBrainMouseSample rnaMouseBrainMouseSample : parseTsvFile(workspace,
                                                                                    ProteinAtlasUpdater.RNA_MOUSE_BRAIN_MOUSE_SAMPLE_FILE_NAME,
                                                                                    RnaMouseBrainMouseSample.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaMouseBrainMouseSample.ensmusgId);
            final Node mainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                        rnaMouseBrainMouseSample.mainRegion);
            final Node subRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                       rnaMouseBrainMouseSample.subregion);
            // TODO: Split into two separate fields for gender and age.
            //       Examples: "female 1" or "male 2".
            // TODO: Maybe rename node label?
            final Node mouseDataNode = graph.addNode(MOUSE_DATA_LABEL, "data", rnaMouseBrainMouseSample.animal);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaMouseBrainMouseSample.tpm, null,
                                                                           rnaMouseBrainMouseSample.pTpm, null, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, subRegionNode, "EXPRESSED_IN");
            graph.addEdge(subRegionNode, geneNode, "CONTAINS_EXPRESSED");
            if (!Objects.equals(rnaMouseBrainMouseSample.mainRegion, rnaMouseBrainMouseSample.subregion)) {
                graph.addEdge(mainRegionNode, subRegionNode, "HAS_SUBREGION");
                graph.addEdge(subRegionNode, mainRegionNode, "IS_SUBREGION_OF");
            }
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(mouseDataNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(subRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaMouseBrainSampleHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaMouseBrainSampleHpas...");
        // TODO: Check if the parsing works like this.
        for (final RnaMouseBrainSampleHpa rnaMouseBrainSampleHpa : parseZipTsvFile(workspace,
                                                                                   ProteinAtlasUpdater.RNA_MOUSE_BRAIN_SAMPLE_HPA_FILE_NAME,
                                                                                   RnaMouseBrainSampleHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaMouseBrainSampleHpa.ensmusgId);
            final Node mainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                        rnaMouseBrainSampleHpa.mainRegion);
            final Node subRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                       rnaMouseBrainSampleHpa.subregion);
            // TODO: Split into two separate fields for gender and age.
            //       Examples: "F 1" or "M 2".
            // TODO: Maybe rename node label?
            final Node mouseDataNode = graph.addNode(MOUSE_DATA_LABEL, "data", rnaMouseBrainSampleHpa.animal);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaMouseBrainSampleHpa.tpm, null,
                                                                           rnaMouseBrainSampleHpa.pTpm,
                                                                           rnaMouseBrainSampleHpa.nTpm, null, null,
                                                                           null, null, null, null);

            graph.addEdge(geneNode, subRegionNode, "EXPRESSED_IN");
            graph.addEdge(subRegionNode, geneNode, "CONTAINS_EXPRESSED");
            if (!Objects.equals(rnaMouseBrainSampleHpa.mainRegion, rnaMouseBrainSampleHpa.subregion)) {
                graph.addEdge(mainRegionNode, subRegionNode, "HAS_SUBREGION");
                graph.addEdge(subRegionNode, mainRegionNode, "IS_SUBREGION_OF");
            }
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(mouseDataNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(subRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaPfcBrainHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaPfcBrainHpas...");
        for (final RnaPfcBrainHpa rnaPfcBrainHpa: parseZipTsvFile(workspace,
                                                                  ProteinAtlasUpdater.RNA_PFC_BRAIN_HPA_FILE_NAME,
                                                                  RnaPfcBrainHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaPfcBrainHpa.gene, rnaPfcBrainHpa.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name", rnaPfcBrainHpa.subregion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaPfcBrainHpa.tpm, null,
                                                                           rnaPfcBrainHpa.pTpm, rnaPfcBrainHpa.nTpm,
                                                                           null, null, null, null, null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaPigBrainHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaPigBrainHpas...");
        for (final RnaPigBrainHpa rnaPigBrainHpa: parseZipTsvFile(workspace,
                                                                  ProteinAtlasUpdater.RNA_PIG_BRAIN_HPA_FILE_NAME,
                                                                  RnaPigBrainHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaPigBrainHpa.gene, rnaPigBrainHpa.geneName);
            final Node brainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name", rnaPigBrainHpa.brainRegion);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaPigBrainHpa.tpm, null,
                                                                           rnaPigBrainHpa.pTpm, rnaPigBrainHpa.nTpm,
                                                                           null, null, null, null, null, null);

            graph.addEdge(geneNode, brainRegionNode, "EXPRESSED_IN");
            graph.addEdge(brainRegionNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(brainRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaPigBrainSampleHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaPigBrainSampleHpas...");
        for (final RnaPigBrainSampleHpa rnaPigBrainSampleHpa : parseZipTsvFile(workspace,
                                                                               ProteinAtlasUpdater.RNA_PIG_BRAIN_SAMPLE_HPA_FILE_NAME,
                                                                               RnaPigBrainSampleHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaPigBrainSampleHpa.enssscgId);
            final Node mainRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                        rnaPigBrainSampleHpa.mainRegion);
            final Node subRegionNode = getOrCreateNode(graph, BRAIN_REGION_LABEL, "name",
                                                       rnaPigBrainSampleHpa.subregion);
            // TODO: Split into two separate fields for gender and age.
            //       Examples: "F 1" or "M 2".
            // TODO: Maybe rename node label?
            final Node pigDataNode = graph.addNode(PIG_DATA_LABEL, "data", rnaPigBrainSampleHpa.animal);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaPigBrainSampleHpa.tpm, null,
                                                                           rnaPigBrainSampleHpa.pTpm,
                                                                           rnaPigBrainSampleHpa.nTpm, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, subRegionNode, "EXPRESSED_IN");
            graph.addEdge(subRegionNode, geneNode, "CONTAINS_EXPRESSED");
            if (!Objects.equals(rnaPigBrainSampleHpa.mainRegion, rnaPigBrainSampleHpa.subregion)) {
                graph.addEdge(mainRegionNode, subRegionNode, "HAS_SUBREGION");
                graph.addEdge(subRegionNode, mainRegionNode, "IS_SUBREGION_OF");
            }
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(pigDataNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(subRegionNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaSingleCellClusterDescriptions(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaSingleCellClusterDescriptions...");
        for (final RnaSingleCellClusterDescription rnaSingleCellClusterDescription: parseZipTsvFile(workspace,
                                                                                                    ProteinAtlasUpdater.RNA_SINGLE_CELL_CLUSTER_DESCRIPTION_FILE_NAME,
                                                                                                    RnaSingleCellClusterDescription.class)) {

            final Node cellTypeNode = getOrCreateNode(graph, CELL_TYPE_LABEL, "name",
                                                      rnaSingleCellClusterDescription.cellType);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name",
                                                    rnaSingleCellClusterDescription.tissue);
            final Node cellTypeGroupNode = getOrCreateNode(graph, CELL_TYPE_GROUP_LABEL, "name",
                                                      rnaSingleCellClusterDescription.cellTypeGroup);
            final Node clusterDataNode = graph.addNode(CLUSTER_DATA_LABEL, "label",
                                                       rnaSingleCellClusterDescription.cluster, "cellCount",
                                                       rnaSingleCellClusterDescription.cellCount);

            graph.addEdge(cellTypeNode, cellTypeGroupNode, "BELONGS_TO");
            graph.addEdge(cellTypeGroupNode, cellTypeNode, "INCLUDES");
            graph.addEdge(cellTypeNode, clusterDataNode, "HAS_CLUSTER_DATA");
            graph.addEdge(tissueNode, clusterDataNode, "HAS_CLUSTER_DATA");
        }
    }

    private void addRnaSingleCellTypes(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaSingleCellTypes...");
        for (final RnaSingleCellType rnaSingleCellType: parseZipTsvFile(workspace,
                                                                        ProteinAtlasUpdater.RNA_SINGLE_CELL_TYPE_FILE_NAME,
                                                                        RnaSingleCellType.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaSingleCellType.gene, rnaSingleCellType.geneName);
            final Node cellTypeNode = getOrCreateNode(graph, CELL_TYPE_LABEL, "name", rnaSingleCellType.cellType);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null,
                                                                           rnaSingleCellType.nTpm, null, null, null,
                                                                           null, null, null);

            graph.addEdge(geneNode, cellTypeNode, "EXPRESSED_IN");
            graph.addEdge(cellTypeNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cellTypeNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaSingleCellTypeTissues(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaSingleCellTypeTissues...");
        for (final RnaSingleCellTypeTissue rnaSingleCellTypeTissue: parseZipTsvFile(workspace,
                                                                                    ProteinAtlasUpdater.RNA_SINGLE_CELL_TYPE_TISSUE_FILE_NAME,
                                                                                    RnaSingleCellTypeTissue.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaSingleCellTypeTissue.gene, rnaSingleCellTypeTissue.geneName);
            final Node cellTypeNode = getOrCreateNode(graph, CELL_TYPE_LABEL, "name", rnaSingleCellTypeTissue.cellType);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaSingleCellTypeTissue.tissue);
            final Node clusterDataNode = graph.addNode(CLUSTER_DATA_LABEL, "label", rnaSingleCellTypeTissue.cluster);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null,
                                                                           rnaSingleCellTypeTissue.nTpm, null,
                                                                           null, rnaSingleCellTypeTissue.readCount,
                                                                           null, null, null);

            // TODO: Are these edges logical?
            graph.addEdge(geneNode, cellTypeNode, "EXPRESSED_IN");
            graph.addEdge(cellTypeNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(cellTypeNode, clusterDataNode, "HAS_CLUSTER_DATA");
            graph.addEdge(tissueNode, clusterDataNode, "HAS_CLUSTER_DATA");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(cellTypeNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(tissueNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(clusterDataNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaTissueConsensuses(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaTissueConsensuses...");
        for (final RnaTissueConsensus rnaTissueConsensus: parseZipTsvFile(workspace,
                                                                          ProteinAtlasUpdater.RNA_TISSUE_CONSENSUS_FILE_NAME,
                                                                          RnaTissueConsensus.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaTissueConsensus.gene, rnaTissueConsensus.geneName);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name",
                                                         rnaTissueConsensus.tissue);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null,
                                                                           rnaTissueConsensus.nTpm, null, null, null,
                                                                           null, null, null);

            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(tissueNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaTissueFantoms(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaTissueFantoms...");
        for (final RnaTissueFantom rnaTissueFantom: parseZipTsvFile(workspace,
                                                                    ProteinAtlasUpdater.RNA_TISSUE_FANTOM_FILE_NAME,
                                                                    RnaTissueFantom.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaTissueFantom.gene, rnaTissueFantom.geneName);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueFantom.tissue);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, null, null, null, null, null, null,
                                                                           null, rnaTissueFantom.tagsPerMillion,
                                                                           rnaTissueFantom.scaledTagsPerMillion,
                                                                           rnaTissueFantom.normalizedTagsPerMillion);

            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(tissueNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaTissueGtexes(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaTissueGtexes...");
        for (final RnaTissueGtex rnaTissueGtex: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_TISSUE_GTEX_FILE_NAME,
                                                                RnaTissueGtex.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaTissueGtex.gene, rnaTissueGtex.geneName);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueGtex.tissue);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaTissueGtex.tpm, null,
                                                                           rnaTissueGtex.pTpm, rnaTissueGtex.nTpm, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(tissueNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaTissueHpas(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaTissueHpas...");
        for (final RnaTissueHpa rnaTissueHpa: parseZipTsvFile(workspace, ProteinAtlasUpdater.RNA_TISSUE_HPA_FILE_NAME,
                                                              RnaTissueHpa.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, rnaTissueHpa.gene, rnaTissueHpa.geneName);
            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueHpa.tissue);
            final Node expressionMetricsNode = createExpressionMetricsNode(graph, rnaTissueHpa.tpm, null,
                                                                           rnaTissueHpa.pTpm, rnaTissueHpa.nTpm, null,
                                                                           null, null, null, null, null);

            graph.addEdge(geneNode, tissueNode, "EXPRESSED_IN");
            graph.addEdge(tissueNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
            graph.addEdge(tissueNode, expressionMetricsNode, "HAS_EXPRESSION_METRICS");
        }
    }

    private void addRnaTissueHpaDescription(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add RnaTissueHpaDescription...");
        for (final RnaTissueHpaDescription rnaTissueHpaDescription : parseZipTsvFile(workspace,
                                                                                     ProteinAtlasUpdater.RNA_TISSUE_HPA_DESCRIPTION_FILE_NAME,
                                                                                     RnaTissueHpaDescription.class)) {

            final Node tissueNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueHpaDescription.tissue);
            final Node tissueGroupNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueHpaDescription.tissueGroup);
            final Node organNode = getOrCreateNode(graph, TISSUE_LABEL, "name", rnaTissueHpaDescription.organ);

            if (!Objects.equals(rnaTissueHpaDescription.tissue, rnaTissueHpaDescription.tissueGroup)) {
                graph.addEdge(tissueNode, tissueGroupNode, "PART_OF");
                graph.addEdge(tissueGroupNode, tissueNode, "HAS_PART");
            }
            if (!Objects.equals(rnaTissueHpaDescription.tissueGroup, rnaTissueHpaDescription.organ)) {
                graph.addEdge(tissueGroupNode, organNode, "PART_OF");
                graph.addEdge(organNode, tissueGroupNode, "HAS_PART");
            }
        }
    }

    private void addSubcellularLocations(final Workspace workspace, final Graph graph) {
        LOGGER.info("Add SubcellularLocations...");
        for (final SubcellularLocation subcellularLocation: parseZipTsvFile(workspace,
                                                                            ProteinAtlasUpdater.SUBCELLULAR_LOCATION_FILE_NAME,
                                                                            SubcellularLocation.class)) {

            final Node geneNode = getOrCreateGeneNode(graph, subcellularLocation.gene, subcellularLocation.geneName,
                                                      subcellularLocation.reliability);

            final Node locationNode = graph.addNode(LOCATION_LABEL, "mainLocation",
                                                    subcellularLocation.mainLocation, "additionalLocation",
                                                    subcellularLocation.additionalLocation, "extracellularLocation",
                                                    subcellularLocation.extracellularLocation, "enhancedLocation",
                                                    subcellularLocation.enhanced, "supportedLocation",
                                                    subcellularLocation.supported);
            locationNode.setProperty("approvedLocation", subcellularLocation.approved);
            locationNode.setProperty("uncertainLocation", subcellularLocation.uncertain);
            // TODO: This has to be split into GO-Id and GO-Term. Also a list could be added for multiple IDs/terms.
            //       Example: "Cell Junctions (GO:0030054);Cytosol (GO:0005829);Nucleoli fibrillar center (GO:0001650)".
            locationNode.setProperty("goId", subcellularLocation.goId);
            graph.update(locationNode);

            final Node cellMetricsNode = graph.addNode(CELL_METRICS_LABEL, "singleCellVariationIntensity",
                                                       subcellularLocation.singleCellVariationIntensity,
                                                       "singleCellVariation,Spatial",
                                                       subcellularLocation.singleCellVariationSpatial,
                                                       "cellCycleDependency", subcellularLocation.cellCycleDependency);

            graph.addEdge(geneNode, locationNode, "EXPRESSED_IN");
            graph.addEdge(locationNode, geneNode, "CONTAINS_EXPRESSED");
            graph.addEdge(geneNode, cellMetricsNode, "HAS_METRICS");
            graph.addEdge(locationNode, cellMetricsNode, "HAS_METRICS");
        }
    }

    private void addTranscriptRnaBloodcells(final Workspace workspace, final Graph graph) {

    }

    private void addTranscriptRnaBrains(final Workspace workspace, final Graph graph) {

    }

    private void addTranscriptRnaGtexRetinas(final Workspace workspace, final Graph graph) {

    }

    private void addTranscriptRnaMouseBrains(final Workspace workspace, final Graph graph) {

    }

    private void addTranscriptRnaPigBrains(final Workspace workspace, final Graph graph) {

    }

    private void addTranscriptRnaTissues(final Workspace workspace, final Graph graph) {

    }
}
