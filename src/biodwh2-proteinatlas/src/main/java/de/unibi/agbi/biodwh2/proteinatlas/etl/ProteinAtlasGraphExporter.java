package de.unibi.agbi.biodwh2.proteinatlas.etl;

import de.unibi.agbi.biodwh2.core.Workspace;
import de.unibi.agbi.biodwh2.core.etl.GraphExporter;
import de.unibi.agbi.biodwh2.core.exceptions.ExporterException;
import de.unibi.agbi.biodwh2.core.model.graph.Graph;
import de.unibi.agbi.biodwh2.core.model.graph.Node;
import de.unibi.agbi.biodwh2.proteinatlas.ProteinAtlasDataSource;
import de.unibi.agbi.biodwh2.proteinatlas.model.NormalTissue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProteinAtlasGraphExporter extends GraphExporter<ProteinAtlasDataSource> {
    private static final Logger LOGGER = LogManager.getLogger(ProteinAtlasGraphExporter.class);

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
        addNormalTissues(graph, dataSource.normalTissues);
        return true;
    }

    private void addNormalTissues(final Graph graph, final List<NormalTissue> normalTissues) {
        LOGGER.info("Add NormalTissues...");
        for (final NormalTissue normalTissue : normalTissues) {
            final Node node = graph.addNodeFromModel(normalTissue);
        }
    }
}
