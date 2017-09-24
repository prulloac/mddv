package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "uploads")
public class UploadFile{

    @Id
    @GeneratedValue
    private long uploadId;
    private String name;
    private String description;
    private String version;
    private long size;
    private String url;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name = "objects_uploads",
            joinColumns = {@JoinColumn(name = "upload_id")},
            inverseJoinColumns = {@JoinColumn(name = "object_id")}
    )
    private Set<MetadataObject> relatedObject;

    public UploadFile(String name, String description, String version) {
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public long getUploadId() {
        return uploadId;
    }

    public void setUploadId(long uploadId) {
        this.uploadId = uploadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<MetadataObject> getRelatedObject() {
        return relatedObject;
    }

    public void setRelatedObject(Set<MetadataObject> relatedObject) {
        this.relatedObject = relatedObject;
    }
}
