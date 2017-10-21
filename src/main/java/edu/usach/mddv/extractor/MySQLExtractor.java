package edu.usach.mddv.extractor;

import edu.usach.mddv.model.DataRepository;
import edu.usach.mddv.model.TechnicalObjectMetadata;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class MySQLExtractor extends AbstractExtractor{

    private Connection connection;
    private DatabaseMetaData databaseMetaData;
    private SimpleDriverDataSource simpleDriverDataSource;

    private void setDataSource() throws SQLException {
        simpleDriverDataSource.setDriver(new com.mysql.jdbc.Driver());
        simpleDriverDataSource.setUrl(getConnectionParam("url"));
        simpleDriverDataSource.setUsername(getConnectionParam("username"));
        simpleDriverDataSource.setPassword(getConnectionParam("password"));
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        return connection.getMetaData();
    }

    public MySQLExtractor(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    public boolean connect() {
        try {
            connection = simpleDriverDataSource.getConnection();
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
