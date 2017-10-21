package edu.usach.mddv.extractor;

import edu.usach.mddv.model.DataRepository;
import edu.usach.mddv.model.TechnicalObjectMetadata;

import java.util.HashMap;

public abstract class AbstractExtractor {
    private DataRepository dataRepository;

    public AbstractExtractor(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public String getConnectionParam(String param){
        return this.dataRepository.getConnectionParam(param);
    }

    public abstract boolean connect();
    public abstract TechnicalObjectMetadata extract();
}
