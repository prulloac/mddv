package edu.usach.mddv.extractor;

import edu.usach.mddv.model.TechnicalObjectMetadata;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class MySQLExtractor extends AbstractExtractor{

    private Connection connection;
    private DatabaseMetaData databaseMetaData;

    private SimpleDriverDataSource getDataSource() throws SQLException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl(getDatasourceConnectionParams().get("url"));
        dataSource.setUsername(getDatasourceConnectionParams().get("username"));
        dataSource.setPassword(getDatasourceConnectionParams().get("password"));
        return dataSource;
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        return connection.getMetaData();
    }

    public MySQLExtractor(String datasourceName, String datasourceType, String datasourceVersion, String datasourceUrl, String datasourceUsername, String datasourcePassword) {
        super(datasourceName, datasourceType, datasourceVersion);
        setDatasourceConnectionParams("url",datasourceUrl);
        setDatasourceConnectionParams("username",datasourceUsername);
        setDatasourceConnectionParams("password",datasourcePassword);
    }

    @Override
    public boolean connect() {
        try {
            connection = getDataSource().getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TechnicalObjectMetadata extract() {
        return null;
    }

}
