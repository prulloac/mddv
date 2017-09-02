package edu.usach.mddv.extractor;

import edu.usach.mddv.model.Structure;

public class MySQLExtractor extends AbstractExtractor{
    public MySQLExtractor(String datasourceName, String datasourceType, String datasourceVersion) {
        super(datasourceName, datasourceType, datasourceVersion);
    }

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public Structure extract() {
        return null;
    }
}
