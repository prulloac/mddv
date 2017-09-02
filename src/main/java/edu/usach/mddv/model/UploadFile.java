package edu.usach.mddv.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class UploadFile extends AbstractVersionable{
    private long size;
    private String url;

    @ManyToOne
    private User author;

    @ManyToOne
    private MetadataObject relatedObject;

    public UploadFile(String name, String description, String version, User author) {
        super(name, description, version);
        this.author = author;
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
}
