package de.unibi.agbi.biodwh2.unii.etl;

import de.unibi.agbi.biodwh2.core.etl.MappingDescriber;
import de.unibi.agbi.biodwh2.core.model.IdentifierType;
import de.unibi.agbi.biodwh2.core.model.graph.*;

public class UNIIMappingDescriber extends MappingDescriber {
    @Override
    public NodeMappingDescription describe(Graph graph, Node node) {
        if (node.getLabels()[0].endsWith("UNII")) {
            NodeMappingDescription description = new NodeMappingDescription();
            description.type = NodeMappingDescription.NodeType.Compound;
            description.addIdentifier(IdentifierType.UNII, node.getProperty("id"));
            if (node.hasProperty("cas") && node.getProperty("cas") != null)
                description.addIdentifier(IdentifierType.CAS, node.getProperty("cas"));
            if (node.hasProperty("pubchem_cid") && node.getProperty("pubchem_cid") != null)
                description.addIdentifier(IdentifierType.PubChemCompound, node.getProperty("pubchem_cid"));
            if (node.hasProperty("ec") && node.getProperty("ec") != null)
                description.addIdentifier(IdentifierType.EuropeanChemicalsAgencyEC, node.getProperty("ec"));
            if (node.hasProperty("rx_cui") && node.getProperty("rx_cui") != null)
                description.addIdentifier(IdentifierType.RxNormCUI, node.getProperty("rx_cui"));
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