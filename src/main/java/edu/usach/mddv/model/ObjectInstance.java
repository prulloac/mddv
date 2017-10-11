package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "instances")
public class ObjectInstance {

    @Id
    @GeneratedValue
    private long instanceId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String version;

    private String color;

    @ManyToMany(mappedBy = "relatedObject")
    private Set<UploadFileInstance> uploads;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP",nullable = false)
    private Timestamp modificationTime;

    @ManyToOne
    @JoinColumn(name = "metaId",nullable = false)
    private AbstractObjectMetadata objectMetadata;

    public ObjectInstance() {
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<UploadFileInstance> getUploads() {
        return uploads;
    }

    public void setUploads(Set<UploadFileInstance> uploads) {
        this.uploads = uploads;
    }

    public Timestamp getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Timestamp modificationTime) {
        this.modificationTime = modificationTime;
    }

    public AbstractObjectMetadata getObjectMetadata() {
        return objectMetadata;
    }

    public void setObjectMetadata(AbstractObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }
}
