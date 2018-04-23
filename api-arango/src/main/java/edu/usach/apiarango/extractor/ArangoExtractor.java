package edu.usach.apiarango.extractor;

import com.arangodb.ArangoDB;
import com.arangodb.model.CollectionsReadOptions;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.NoSQLExtractor;
import org.json.simple.JSONObject;

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
	public JSONObject extract(ConnectionParamsDTO connectionParams) {
		ArangoDB connection = new ArangoDB.Builder().host(connectionParams.getHost(),connectionParams.getPort()).user(connectionParams.getUsername()).password(connectionParams.getPassword()).build();
		JSONObject object = new JSONObject();
		CollectionsReadOptions collectionsReadOptions = new CollectionsReadOptions();
		collectionsReadOptions.excludeSystem(true);
		object.put("collections",connection.db(connectionParams.getDatabase()).getCollections(collectionsReadOptions));
		return object;
	}
}
