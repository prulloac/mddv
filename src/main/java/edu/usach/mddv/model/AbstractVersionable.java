package edu.usach.mddv.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class AbstractVersionable extends AbstractDescriptable {
    private String version;
    private Timestamp creationTime;

    public AbstractVersionable(String name, String description, String version) {
        super(name, description);
        this.version = version;
        this.creationTime = new Timestamp(System.currentTimeMillis());
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

}
