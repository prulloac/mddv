package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractAuditableNamedDescriptableEntity;
import edu.usach.apicommons.model.IEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document extends AbstractAuditableNamedDescriptableEntity implements IEntity {
	private String version;
	private String size;
	private String url;
	@ManyToOne
	@JoinColumn(name = "uploaderId")
	private User uploader;
	@ManyToMany(mappedBy = "documentList")
	private List<AbstractMetadataObject> metadataObjectList;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
	}

	public List<AbstractMetadataObject> getMetadataObjectList() {
		return metadataObjectList;
	}

	public void setMetadataObjectList(List<AbstractMetadataObject> metadataObjectList) {
		this.metadataObjectList = metadataObjectList;
	}
}
