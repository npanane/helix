package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 8/15/2014.
 */
@Entity
@Table(name = "clienttype")
public class ClientType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdClientType")
    private Integer idClientType;

    @Column(name = "ClientType")
    private String clientType;

    @Column(name = "flStatus" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOn")
    private Date createdOn;

    public Integer getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(Integer idClientType) {
        this.idClientType = idClientType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Boolean flStatus) {
        this.flStatus = flStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
