package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.DataSource;
import de.unibi.agbi.biodwh2.core.etl.MappingDescriber;
import de.unibi.agbi.biodwh2.core.model.graph.*;

public class ProteinAtlasMappingDescriber extends MappingDescriber {
    public ProteinAtlasMappingDescriber(final DataSource dataSource) {
        super(dataSource);
    }

    // WIP
    @Override
    public NodeMappingDescription[] describe(final Graph graph, final Node node, final String localMappingLabel) {
        return null;
    }

    // WIP
    @Override
    protected String[] getNodeMappingLabels() {
        return new String[0];
    }

    // WIP
    @Override
    public PathMappingDescription describe(final Graph graph, final Node[] nodes, final Edge[] edges) {
        return null;
    }

    // WIP
    @Override
    protected PathMapping[] getEdgePathMappings() {
        return new PathMapping[0];
    }
}
