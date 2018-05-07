package edu.usach.apimongo.extractor;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.util.Arrays;
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
	public JSONObject connectionParameters() {
		JSONObject parameters = new JSONObject();
		parameters.put("database","string");
		parameters.put("username","string");
		parameters.put("password","string");
		parameters.put("authDatabase","string");
		parameters.put("host","string");
		parameters.put("port","int");
		return parameters;
	}

	public JSONObject extract(Map<String, Object> connectionParams) {
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
		Document collections = mongoDatabase.runCommand(new Document("listCollections", 1));
    object.put("collections", collections);
    mongoClient.close();
		return object;
	}
}
