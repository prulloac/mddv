package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractAuditableDescriptableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document extends AbstractAuditableDescriptableEntity implements IEntity {
	private String version;
	private String size;
	private String url;
	@ManyToOne
	@JoinColumn(name = "uploaderId")
	@JsonIgnore
	private User uploader;
	@ManyToMany(mappedBy = "documentList")
	@JsonIgnore
	private List<MetadataObject> metadataObjectList;

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

	public List<MetadataObject> getMetadataObjectList() {
		return metadataObjectList;
	}

	public void setMetadataObjectList(List<MetadataObject> metadataObjectList) {
		this.metadataObjectList = metadataObjectList;
	}
}
