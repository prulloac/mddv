package edu.usach.mddv.extractor;

import edu.usach.mddv.model.TechnicalObjectMetadata;

public class ArangoDBExtractor extends AbstractExtractor {
    public ArangoDBExtractor(String datasourceName, String datasourceType, String datasourceVersion) {
        super(datasourceName, datasourceType, datasourceVersion);
    }

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public TechnicalObjectMetadata extract() {
        return null;
    }
}
