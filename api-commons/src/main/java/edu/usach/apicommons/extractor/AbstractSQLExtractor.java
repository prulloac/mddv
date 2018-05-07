package edu.usach.apicommons.extractor;

import java.sql.Connection;
import java.util.Map;

public abstract class AbstractSQLExtractor extends AbstractExtractor implements SQLExtractor {

    protected Connection connection = null;

    protected abstract String jdbcUrl(Map<String, Object> connectionParamsDTO);

}
