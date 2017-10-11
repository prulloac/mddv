package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "uploads")
public class UploadFileInstance {

    @Id
    @GeneratedValue
    private long uploadId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String version;

    private long size;

    private String url;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp modificationTime;

    @ManyToOne
    @JoinColumn(name = "uploadMetadataId",nullable = false)
    private UploadFileMetadata fileMeta;

    @ManyToMany
    @JoinTable(
            name = "objectsUploads",
            joinColumns = {@JoinColumn(name = "uploadId")},
            inverseJoinColumns = {@JoinColumn(name = "objectId")}
    )
    private Set<ObjectInstance> relatedObject;

    public UploadFileInstance() {
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

    public Timestamp getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Timestamp modificationTime) {
        this.modificationTime = modificationTime;
    }

    public UploadFileMetadata getFileMeta() {
        return fileMeta;
    }

    public void setFileMeta(UploadFileMetadata fileMeta) {
        this.fileMeta = fileMeta;
    }

    public Set<ObjectInstance> getRelatedObject() {
        return relatedObject;
    }

    public void setRelatedObject(Set<ObjectInstance> relatedObject) {
        this.relatedObject = relatedObject;
    }
}
