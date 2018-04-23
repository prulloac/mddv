package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;

import java.sql.Connection;

public abstract class AbstractSQLExtractor extends AbstractExtractor implements SQLExtractor {

    protected Connection connection = null;

    protected abstract String jdbcUrl(ConnectionParamsDTO connectionParamsDTO);

}
