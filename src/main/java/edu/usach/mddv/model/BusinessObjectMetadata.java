package edu.usach.mddv.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("business")
public class BusinessObjectMetadata extends AbstractObjectMetadata{

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    public BusinessObjectMetadata() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
