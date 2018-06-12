package edu.usach.apimongo.extractor;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import edu.usach.apicommons.dto.NoSQLCollectionDTO;
import edu.usach.apicommons.dto.NoSQLDocumentExtractionDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MongoExtractor extends AbstractExtractor implements NoSQLExtractor {
	@Override
	public String databaseEngine() {
		return "MongoDB";
	}

	@Override
	public String databaseType() {
		return "Document";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"3.4"};
	}

	@Override
	public List<Map<String, Object>> connectionParameters() {
		List<Map<String, Object>> parameters = new ArrayList<>();
		parameters.add(newConnectionParameter("text","host","Host"));
		parameters.add(newConnectionParameter("number","port","Puerto"));
		parameters.add(newConnectionParameter("text","database","Base de datos"));
		parameters.add(newConnectionParameter("text","username","Usuario"));
		parameters.add(newConnectionParameter("password","password","Contraseña"));
		parameters.add(newConnectionParameter("text","authDatabase","Base de datos de autenticación"));
		return parameters;
	}

	@Override
	public NoSQLDocumentExtractionDTO extract(Map<String, Object> connectionParams) {
		String host = (String) connectionParams.get("host");
		int port = (int) connectionParams.get("port");
		String username = (String) connectionParams.get("username");
		String authDatabase = (String) connectionParams.get("authDatabase");
		String password = (String) connectionParams.get("password");
		String database = (String) connectionParams.get("database");
		JSONObject object = new JSONObject();
		MongoCredential credential = MongoCredential.createCredential(username, authDatabase, password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		List<NoSQLCollectionDTO> collections = new ArrayList<>();
		for(String s : mongoDatabase.listCollectionNames())
			collections.add(NoSQLCollectionDTO.of(s));
		NoSQLDocumentExtractionDTO extractionDTO = NoSQLDocumentExtractionDTO.of(collections, databaseType());
    object.put("collections", collections);
		extractionDTO.setRepositoryType(databaseType());
		extractionDTO.setRepositoryEngine(databaseEngine());
		extractionDTO.setRepositoryEngineVersion(mongoDatabase.runCommand(Document.parse("{buildInfo: 1}")).getString("version"));
		mongoClient.close();
		return extractionDTO;
	}
}
