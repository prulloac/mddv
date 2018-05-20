package edu.usach.apiarango.extractor;

import com.arangodb.ArangoDB;
import com.arangodb.model.CollectionsReadOptions;
import edu.usach.apicommons.dto.NoSQLCollectionDTO;
import edu.usach.apicommons.dto.NoSQLDocumentExtractionDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;
import org.json.simple.JSONObject;

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
	public JSONObject connectionParameters() {
		JSONObject parameters = new JSONObject();
		parameters.put("database","string");
		parameters.put("username","string");
		parameters.put("password","string");
		parameters.put("host","string");
		parameters.put("port","int");
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
}
