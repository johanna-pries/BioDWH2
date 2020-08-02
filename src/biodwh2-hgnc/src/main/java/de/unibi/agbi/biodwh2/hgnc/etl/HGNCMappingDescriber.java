package de.unibi.agbi.biodwh2.hgnc.etl;

import de.unibi.agbi.biodwh2.core.etl.MappingDescriber;
import de.unibi.agbi.biodwh2.core.model.IdentifierType;
import de.unibi.agbi.biodwh2.core.model.graph.*;

public class HGNCMappingDescriber extends MappingDescriber {
    @Override
    public NodeMappingDescription describe(Graph graph, Node node) {
        if (node.getLabel().endsWith("Gene")) {
            NodeMappingDescription description = new NodeMappingDescription();
            description.type = NodeMappingDescription.NodeType.GENE;
            description.addIdentifier(IdentifierType.HGNC_ID, node.<String>getProperty("hgnc_id").replace("HGNC:", ""));
            description.addIdentifier(IdentifierType.HGNC_SYMBOL, node.getProperty("symbol"));
            if (node.hasProperty("omim_id"))
                description.addIdentifier(IdentifierType.OMIM, node.getProperty("omim_id"));
            // TODO: more ids
            return description;
        }
        return null;
    }

    @Override
    public EdgeMappingDescription describe(Graph graph, Edge edge) {
        return null;
    }
}
