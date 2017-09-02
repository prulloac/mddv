package edu.usach.mddv.extractor;

import edu.usach.mddv.model.Structure;

import java.util.HashMap;

public abstract class AbstractExtractor {
    private String datasourceName;
    private String datasourceType;
    private String datasourceVersion;
    private HashMap<String,String> datasourceConnectionParams;

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType;
    }

    public String getDatasourceVersion() {
        return datasourceVersion;
    }

    public void setDatasourceVersion(String datasourceVersion) {
        this.datasourceVersion = datasourceVersion;
    }

    public HashMap<String, String> getDatasourceConnectionParams() {
        return datasourceConnectionParams;
    }

    public void setDatasourceConnectionParams(HashMap<String, String> datasourceConnectionParams) {
        this.datasourceConnectionParams = datasourceConnectionParams;
    }

    public AbstractExtractor(String datasourceName, String datasourceType, String datasourceVersion) {
        this.datasourceName = datasourceName;
        this.datasourceType = datasourceType;
        this.datasourceVersion = datasourceVersion;
    }

    public abstract boolean connect();
    public abstract Structure extract();
}
