package de.unibi.agbi.biodwh2.guidetopharmacology;

import de.unibi.agbi.biodwh2.core.DataSource;
import de.unibi.agbi.biodwh2.core.DevelopmentState;
import de.unibi.agbi.biodwh2.core.etl.*;
import de.unibi.agbi.biodwh2.guidetopharmacology.etl.GuideToPharmacologyGraphExporter;
import de.unibi.agbi.biodwh2.guidetopharmacology.etl.GuideToPharmacologyMappingDescriber;
import de.unibi.agbi.biodwh2.guidetopharmacology.etl.GuideToPharmacologyUpdater;

public class GuideToPharmacologyDataSource extends DataSource {
    @Override
    public String getId() {
        return "GuideToPharmacology";
    }

    @Override
    public String getLicense() {
        return "CC BY-SA 4.0";
    }

    @Override
    public DevelopmentState getDevelopmentState() {
        return DevelopmentState.InDevelopment;
    }

    @Override
    protected Updater<? extends DataSource> getUpdater() {
        return new GuideToPharmacologyUpdater(this);
    }

    @Override
    protected Parser<? extends DataSource> getParser() {
        return new PassThroughParser<>(this);
    }

    @Override
    protected GraphExporter<? extends DataSource> getGraphExporter() {
        return new GuideToPharmacologyGraphExporter(this);
    }

    @Override
    public MappingDescriber getMappingDescriber() {
        return new GuideToPharmacologyMappingDescriber(this);
    }

    @Override
    protected void unloadData() {
    }
}
