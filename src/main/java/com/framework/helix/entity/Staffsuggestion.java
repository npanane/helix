package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 9/3/2014.
 */

@Entity
@Table(name = "staffsuggestion")
public class Staffsuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSuggestion")
    private Integer idSuggestion;

    @Column(name = "Suggestion")
    private String Suggestion;

    @Column(name = "flComplete" , columnDefinition = "boolean default false")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flComplete;

    @Column(name = "flStatus", columnDefinition = "boolean default true")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserCreated",referencedColumnName = "idUser")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated")
    private Date DateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserLastUpdated",referencedColumnName = "idUser")
    private User idUserLastUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpdated")
    private Date DateUpdated;


    public Integer getIdSuggestion() {
        return idSuggestion;
    }

    public void setIdSuggestion(Integer idSuggestion) {
        this.idSuggestion = idSuggestion;
    }

    public String getSuggestion() {
        return Suggestion;
    }

    public void setSuggestion(String suggestion) {
        Suggestion = suggestion;
    }

    public Boolean getFlComplete() {
        return flComplete;
    }

    public void setFlComplete(Boolean flComplete) {
        this.flComplete = flComplete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Boolean flStatus) {
        this.flStatus = flStatus;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public User getIdUserLastUpdated() {
        return idUserLastUpdated;
    }

    public void setIdUserLastUpdated(User idUserLastUpdated) {
        this.idUserLastUpdated = idUserLastUpdated;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }
}
