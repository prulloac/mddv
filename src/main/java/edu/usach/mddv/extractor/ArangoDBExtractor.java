package edu.usach.mddv.extractor;

import edu.usach.mddv.model.DataRepository;
import edu.usach.mddv.model.TechnicalObjectMetadata;

public class ArangoDBExtractor extends AbstractExtractor {

    public ArangoDBExtractor(DataRepository dataRepository) {
        super(dataRepository);
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
