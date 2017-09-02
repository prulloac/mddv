package edu.usach.mddv.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Structure extends TechnicalObject {

    @OneToMany(mappedBy = "parentObject")
    private Set<TechnicalObject> elements;

    public Structure(String name, String description, String version, String type) {
        super(name, description, version, type);
    }

    public Set<TechnicalObject> getElements() {
        return elements;
    }

    public void setElements(Set<TechnicalObject> elements) {
        this.elements = elements;
    }
}
