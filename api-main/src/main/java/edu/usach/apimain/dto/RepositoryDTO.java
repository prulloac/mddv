package edu.usach.apimain.dto;

import edu.usach.apimain.model.Repository;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RepositoryDTO {

	private String name;
	private String version;
	private String location;
	private Boolean outsourced;
	private Boolean hasConnectionParams;
	private String type;
	private long id;
	private Map<String, Object> connectionParams;

	public RepositoryDTO(Repository repository) {
		this.name = repository.getName();
		this.location = repository.getLocation();
		this.type = repository.getType();
		this.version = repository.getVersion();
		this.outsourced = repository.getOutsourced();
		this.hasConnectionParams = !repository.getConnectionParameters().isEmpty();
		this.id = repository.getId();
		this.connectionParams = new HashMap<>();
		if(this.hasConnectionParams)
			repository.getConnectionParameters().forEach(x -> connectionParams.put(x.getName(), x.getValue()));
	}

}
