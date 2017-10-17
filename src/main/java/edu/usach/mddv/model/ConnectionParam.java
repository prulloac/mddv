package edu.usach.mddv.model;

import javax.persistence.*;

@Entity
@Table(name = "connectionParams")
public class ConnectionParam {

    @Id
    @GeneratedValue
    private long paramsId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "repositoryId")
    private DataRepository dataRepository;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    public ConnectionParam() {
    }

    public long getParamsId() {
        return paramsId;
    }

    public void setParamsId(long paramsId) {
        this.paramsId = paramsId;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
