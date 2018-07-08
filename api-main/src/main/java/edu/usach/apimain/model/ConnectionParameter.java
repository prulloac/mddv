package edu.usach.apimain.model;

import edu.usach.apicommons.model.AbstractNamedEntity;
import edu.usach.apicommons.model.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ConnectionParameter extends AbstractNamedEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    private String value;
    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private Repository repository;

}
