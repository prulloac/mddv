package edu.usach.mddv.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractDescriptable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;

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

    public AbstractDescriptable(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
