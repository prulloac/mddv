package edu.usach.apimain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "businessObject")
public class BusinessObject extends AbstractMetadataObject implements Serializable {
}
