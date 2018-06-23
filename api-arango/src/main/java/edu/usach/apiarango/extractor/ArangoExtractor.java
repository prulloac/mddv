package edu.usach.apiarango.extractor;

import com.arangodb.ArangoDB;
import com.arangodb.model.CollectionsReadOptions;
import edu.usach.apicommons.dto.NoSQLCollectionDTO;
import edu.usach.apicommons.dto.NoSQLDocumentExtractionDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ArangoExtractor extends AbstractExtractor implements NoSQLExtractor {
	@Override
	public String databaseEngine() {
		return "ArangoDB";
	}

	@Override
	public String databaseType() {
		return "Document";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"3.1"};
	}

	@Override
	public List<Map<String, Object>> connectionParameters() {
		List<Map<String, Object>> parameters = new ArrayList<>();
		parameters.add(newConnectionParameter("text","host","Host"));
		parameters.add(newConnectionParameter("number","port","Puerto"));
		parameters.add(newConnectionParameter("text","database","Base de datos"));
		parameters.add(newConnectionParameter("text","username","Usuario"));
		parameters.add(newConnectionParameter("password","password","Contraseña"));
		return parameters;
	}

	@Override
	public NoSQLDocumentExtractionDTO extract(Map<String, Object> connectionParams) {
		String host = (String) connectionParams.get("host");
		int port = (int) connectionParams.get("port");
		String username = (String) connectionParams.get("username");
		String password = (String) connectionParams.get("password");
		String database = (String) connectionParams.get("database");
		ArangoDB connection = new ArangoDB.Builder().host(host,port).user(username).password(password).build();
		CollectionsReadOptions collectionsReadOptions = new CollectionsReadOptions();
		collectionsReadOptions.excludeSystem(true);
		List<NoSQLCollectionDTO> collections = new ArrayList<>();
		connection.db(database).getCollections(collectionsReadOptions).forEach(x -> collections.add(NoSQLCollectionDTO.of(x.getName())));
		NoSQLDocumentExtractionDTO extractionDTO = NoSQLDocumentExtractionDTO.of(collections, databaseType());
		extractionDTO.setRepositoryEngine(databaseEngine());
		extractionDTO.setRepositoryEngineVersion(connection.getVersion().getVersion());
		return extractionDTO;
	}

	@Override
	public Boolean testConnection(Map<String, Object> connectionParams) {
		String host = (String) connectionParams.get("host");
		int port = (int) connectionParams.get("port");
		String username = (String) connectionParams.get("username");
		String password = (String) connectionParams.get("password");
		String database = (String) connectionParams.get("database");
		ArangoDB connection = new ArangoDB.Builder().host(host,port).user(username).password(password).build();
		return connection.db(database).exists();
	}
}
