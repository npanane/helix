package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */

@Entity
@Table(name = "noticeofintent")
public class Noticeofintent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idIntent")
    private Integer idIntent;

    @Column(name = "RegistrationNo")
    private String RegistrationNo;

    @Column(name = "Email")
    private String Email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BeginningDate")
    private Date BeginningDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndingDate")
    private Date EndingDate;

    @Column(name = "Activity")
    private String Activity;

    @Column(name = "flTelemarketing" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flTelemarketing;

    @Column(name = "flStatus" )
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;


    public Integer getIdIntent() {
        return idIntent;
    }

    public void setIdIntent(Integer idIntent) {
        this.idIntent = idIntent;
    }

    public String getRegistrationNo() {
        return RegistrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        RegistrationNo = registrationNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBeginningDate() {
        return BeginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        BeginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(Date endingDate) {
        EndingDate = endingDate;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public Boolean getFlTelemarketing() {
        return flTelemarketing;
    }

    public void setFlTelemarketing(Boolean flTelemarketing) {
        this.flTelemarketing = flTelemarketing;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getIdUserLastUpdated() {
        return idUserLastUpdated;
    }

    public void setIdUserLastUpdated(User idUserLastUpdated) {
        this.idUserLastUpdated = idUserLastUpdated;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }
}
