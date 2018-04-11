package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractSQLExtractor extends AbstractExtractor implements SQLExtractor {

    protected Connection connection = null;

    protected abstract String jdbcUrl();

    public AbstractSQLExtractor(ConnectionParamsDTO connectionParams) {
        super(connectionParams);
    }

    @Override
    public void connect() {
        logger.info("Attempting to connect to " + jdbcUrl());
        try {
            if (connectionParams().isAuthrRequired())
                connection = DriverManager.getConnection(jdbcUrl(), connectionParams().getUsername(), connectionParams().getPassword());
            else
                connection = DriverManager.getConnection(jdbcUrl());
            logger.info("Connected to " + jdbcUrl());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String databaseType() {
        return "SQL";
    }

}
