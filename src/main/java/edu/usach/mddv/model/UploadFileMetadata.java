package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "uploadsMetadata")
public class UploadFileMetadata {

    @Id
    @GeneratedValue
    private long uploadMetaId;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp creationTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    @OneToMany(mappedBy = "fileMeta",orphanRemoval = true)
    private Set<UploadFileInstance> instances;

    public UploadFileMetadata() {
    }

    public long getUploadMetaId() {
        return uploadMetaId;
    }

    public void setUploadMetaId(long uploadMetaId) {
        this.uploadMetaId = uploadMetaId;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<UploadFileInstance> getInstances() {
        return instances;
    }

    public void setInstances(Set<UploadFileInstance> instances) {
        this.instances = instances;
    }
}
