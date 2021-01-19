package de.unibi.agbi.biodwh2.core.etl;

import de.unibi.agbi.biodwh2.core.DataSource;
import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.graphics.MetaGraphImage;
import de.unibi.agbi.biodwh2.core.io.graph.GraphMLGraphWriter;
import de.unibi.agbi.biodwh2.core.model.graph.*;
import de.unibi.agbi.biodwh2.core.model.graph.meta.MetaGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public final class GraphMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphMapper.class);
    private static final String MAPPED_TO_EDGE_LABEL = "MAPPED_TO";
    private static final String IDS_NODE_PROPERTY = "ids";
    private static final String NAMES_NODE_PROPERTY = "names";
    private static final String MAPPED_NODE_PROPERTY = "__mapped";

    public void map(final Workspace workspace, final DataSource[] dataSources, final String inputGraphFilePath,
                    final String outputGraphFilePath) {
        copyGraph(inputGraphFilePath, outputGraphFilePath);
        final String graphFilePath = outputGraphFilePath.replace(GraphFileFormat.GRAPH_ML.extension, Graph.EXTENSION);
        try (final Graph graph = new Graph(graphFilePath, true)) {
            mapGraph(graph, dataSources);
            saveGraph(graph, outputGraphFilePath);
            saveMetaGraph(graph, workspace.getMappedMetaGraphOutputFilePath());
        }
    }

    private void copyGraph(final String inputGraphFilePath, final String outputGraphFilePath) {
        final Path originalPath = Paths.get(
                inputGraphFilePath.replace(GraphFileFormat.GRAPH_ML.extension, Graph.EXTENSION));
        final Path copied = Paths.get(outputGraphFilePath.replace(GraphFileFormat.GRAPH_ML.extension, Graph.EXTENSION));
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled())
                LOGGER.error("Failed to copy merged graph to mapped graph file", e);
        }
    }

    private void mapGraph(final Graph graph, final DataSource[] dataSources) {
        final Map<String, MappingDescriber> map = getDataSourceDescriberMap(dataSources);
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Mapping nodes");
        mapNodes(graph, map);
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Mapping paths");
        mapPaths(graph, map);
    }

    private Map<String, MappingDescriber> getDataSourceDescriberMap(final DataSource[] dataSources) {
        final Map<String, MappingDescriber> map = new HashMap<>();
        for (final DataSource dataSource : dataSources)
            map.put(dataSource.getId(), dataSource.getMappingDescriber());
        return map;
    }

    private void mapNodes(final Graph graph, final Map<String, MappingDescriber> dataSourceDescriberMap) {
        final Map<String, Set<Long>> idNodeIdMap = new HashMap<>();
        for (final MappingDescriber describer : dataSourceDescriberMap.values()) {
            final String[] localMappingLabels = describer.getNodeMappingLabels();
            for (final String localMappingLabel : localMappingLabels) {
                final String prefixedMappingLabel = describer.prefixLabel(localMappingLabel);
                if (LOGGER.isInfoEnabled())
                    LOGGER.info("Mapping nodes with label '" + prefixedMappingLabel + "'");
                for (final Node node : graph.getNodes(prefixedMappingLabel)) {
                    final NodeMappingDescription[] mappingDescriptions = describer.describe(graph, node,
                                                                                            localMappingLabel);
                    if (mappingDescriptions != null)
                        for (final NodeMappingDescription mappingDescription : mappingDescriptions)
                            mergeMatchingNodes(graph, mappingDescription, idNodeIdMap, node.getId());
                }
            }
        }
    }

    private void mergeMatchingNodes(final Graph graph, final NodeMappingDescription description,
                                    final Map<String, Set<Long>> idNodeIdMap, final long mappedNodeId) {
        final Set<Long> matchedNodeIds = matchNodesFromIds(idNodeIdMap, description);
        final Node mergedNode = mergeOrCreateMappingNode(graph, description, matchedNodeIds, idNodeIdMap);
        graph.addEdge(mappedNodeId, mergedNode, MAPPED_TO_EDGE_LABEL);
        for (final String id : mergedNode.<Set<String>>getProperty(IDS_NODE_PROPERTY)) {
            if (!idNodeIdMap.containsKey(id))
                idNodeIdMap.put(id, new HashSet<>());
            idNodeIdMap.get(id).add(mergedNode.getId());
        }
    }

    private Set<Long> matchNodesFromIds(final Map<String, Set<Long>> idNodeIdMap,
                                        final NodeMappingDescription description) {
        final Set<Long> matchedNodeIds = new HashSet<>();
        for (final String id : description.getIdentifiers())
            if (idNodeIdMap.containsKey(id))
                matchedNodeIds.addAll(idNodeIdMap.get(id));
        return matchedNodeIds;
    }

    private Node mergeOrCreateMappingNode(final Graph graph, final NodeMappingDescription description,
                                          final Set<Long> matchedNodeIds, final Map<String, Set<Long>> idNodeIdMap) {
        final Set<String> ids = new HashSet<>(description.getIdentifiers());
        final Set<String> names = description.getNames();
        final int idsCount = ids.size();
        final int namesCount = names.size();
        Node mergedNode = null;
        for (final Long nodeId : matchedNodeIds) {
            final Node matchedNode = graph.getNode(nodeId);
            if (!hasMatchedNodeSameLabel(description, matchedNode))
                continue;
            final Set<String> nodeIds = matchedNode.getProperty(IDS_NODE_PROPERTY);
            ids.addAll(nodeIds);
            final Set<String> nodeNames = matchedNode.getProperty(NAMES_NODE_PROPERTY);
            if (nodeNames != null)
                names.addAll(nodeNames);
            if (mergedNode == null)
                mergedNode = matchedNode;
            else {
                graph.mergeNodes(mergedNode, matchedNode);
                for (final String id : nodeIds)
                    idNodeIdMap.get(id).remove(nodeId);
            }
        }
        if (mergedNode == null) {
            mergedNode = graph.addNode(description.type.toString(), MAPPED_NODE_PROPERTY, true, IDS_NODE_PROPERTY, ids,
                                       NAMES_NODE_PROPERTY, names);
        } else {
            if (idsCount != ids.size() || namesCount != names.size()) {
                mergedNode.setProperty(IDS_NODE_PROPERTY, ids);
                mergedNode.setProperty(NAMES_NODE_PROPERTY, names);
                graph.update(mergedNode);
            }
        }
        return mergedNode;
    }

    private boolean hasMatchedNodeSameLabel(final NodeMappingDescription description, final Node node) {
        return description.type.toString().equals(node.getLabels()[0]);
    }

    private void mapPaths(final Graph graph, final Map<String, MappingDescriber> dataSourceDescriberMap) {
        for (final MappingDescriber describer : dataSourceDescriberMap.values())
            for (final String[] path : describer.getPrefixedEdgeMappingPaths())
                mapPath(graph, describer, path);
    }

    private void mapPath(final Graph graph, final MappingDescriber describer, final String[] path) {
        logPath(path);
        for (Node node : graph.getNodes(path[0])) {
            final long[] currentPathIds = new long[path.length];
            currentPathIds[0] = node.getId();
            buildPathRecursively(graph, describer, path, 1, currentPathIds);
        }
    }

    private static void logPath(final String[] path) {
        if (LOGGER.isInfoEnabled()) {
            StringBuilder builder = new StringBuilder("Mapping edge paths ");
            for (int i = 0; i < path.length; i++) {
                if (i > 0)
                    builder.append("-");
                builder.append(i % 2 == 0 ? "(:" : "[:").append(path[i]).append(i % 2 == 0 ? ")" : "]");
            }
            LOGGER.info(builder.toString());
        }
    }

    private void buildPathRecursively(final Graph graph, final MappingDescriber describer, final String[] path,
                                      final int edgeIndex, final long[] currentPathIds) {
        if (edgeIndex >= path.length) {
            mapPathInstance(graph, describer, currentPathIds);
            return;
        }
        for (final Edge edge : graph.findEdges(path[edgeIndex], Edge.FROM_ID_FIELD, currentPathIds[edgeIndex - 1])) {
            currentPathIds[edgeIndex] = edge.getId();
            final Node nextNode = graph.getNode(edge.getToId());
            if (nextNode.getLabels()[0].equals(path[edgeIndex + 1])) {
                final long[] nextPathIds = Arrays.copyOf(currentPathIds, currentPathIds.length);
                nextPathIds[edgeIndex + 1] = nextNode.getId();
                buildPathRecursively(graph, describer, path, edgeIndex + 2, nextPathIds);
            }
        }
        for (final Edge edge : graph.findEdges(path[edgeIndex], Edge.TO_ID_FIELD, currentPathIds[edgeIndex - 1])) {
            currentPathIds[edgeIndex] = edge.getId();
            final Node nextNode = graph.getNode(edge.getFromId());
            if (nextNode.getLabels()[0].equals(path[edgeIndex + 1])) {
                final long[] nextPathIds = Arrays.copyOf(currentPathIds, currentPathIds.length);
                nextPathIds[edgeIndex + 1] = nextNode.getId();
                buildPathRecursively(graph, describer, path, edgeIndex + 2, nextPathIds);
            }
        }
    }

    private void mapPathInstance(final Graph graph, final MappingDescriber describer, final long[] pathIds) {
        //if (LOGGER.isInfoEnabled())
        //    LOGGER.info("Path instance IDs " + StringUtils.join(pathIds, ';'));
        final Node[] nodes = new Node[pathIds.length / 2 + 1];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = graph.getNode(pathIds[i * 2]);
        final Edge[] edges = new Edge[pathIds.length / 2];
        for (int i = 0; i < edges.length; i++)
            edges[i] = graph.getEdge(pathIds[i * 2 + 1]);
        final PathMappingDescription mappingDescription = describer.describe(graph, nodes, edges);
        if (mappingDescription != null) {
            final Long[] mappedFromNodeIds = graph.getAdjacentNodeIdsForEdgeLabel(pathIds[0], MAPPED_TO_EDGE_LABEL);
            final Long[] mappedToNodeIds = graph.getAdjacentNodeIdsForEdgeLabel(pathIds[pathIds.length - 1],
                                                                                MAPPED_TO_EDGE_LABEL);
            if (mappedFromNodeIds.length > 0 && mappedToNodeIds.length > 0) {
                final Edge mappedEdge = graph.addEdge(mappedFromNodeIds[0], mappedToNodeIds[0],
                                                      mappingDescription.type.name());
                mappedEdge.setProperty("source", describer.getDataSourceId());
            }
        }
    }

    private void saveGraph(final Graph graph, final String outputGraphFilePath) {
        final GraphMLGraphWriter graphMLWriter = new GraphMLGraphWriter();
        graphMLWriter.write(outputGraphFilePath, graph);
    }

    private void saveMetaGraph(final Graph graph, final String outputFilePath) {
        if (LOGGER.isInfoEnabled())
            LOGGER.info("Generating mapped meta graph image");
        final MetaGraph metaGraph = new MetaGraph(graph);
        final MetaGraphImage image = new MetaGraphImage(metaGraph, 2048, 2048);
        image.drawAndSaveImage(outputFilePath);
    }
}
