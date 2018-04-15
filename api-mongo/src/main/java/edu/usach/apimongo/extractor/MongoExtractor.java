package edu.usach.apimongo.extractor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;
import edu.usach.apimongo.dto.MongoConnectionParamsDTO;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.util.Arrays;

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
	@Deprecated
	public JSONObject extract(ConnectionParamsDTO connectionParamsDTO){
		return null;
	}

	public JSONObject extract(MongoConnectionParamsDTO connectionParams) {
		JSONObject object = new JSONObject();
		MongoCredential credential = MongoCredential.createCredential(connectionParams.getUsername(), connectionParams.getAuthDatabase(), connectionParams.getPassword().toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(connectionParams.getHost(), connectionParams.getPort()), Arrays.asList(credential));
		MongoDatabase database = mongoClient.getDatabase(connectionParams.getDatabase());
		Document collections = database.runCommand(new Document("listCollections", 1));
		object.put("collections", collections);
		return object;
	}
}
