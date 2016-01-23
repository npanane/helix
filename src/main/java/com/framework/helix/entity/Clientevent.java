package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */

@Entity
@Table(name = "clientevent")
public class Clientevent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClientEvent")
    private Integer idClientEvent;

    @Column(name = "AlarmDate")
    private Date AlarmDate;

    @Column(name = "flReminder")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flReminder;

    @Column(name = "Notes")
    private String Notes;

    @Column(name = "flStatus")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserCreated",referencedColumnName = "idUser")
    private User user;

    @Column(name = "DateCreated")
    private Date DateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserLastUpdated",referencedColumnName = "idUser")
    private User idUserLastUpdated;

    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;

    public Integer getIdClientEvent() {
        return idClientEvent;
    }

    public void setIdClientEvent(Integer idClientEvent) {
        this.idClientEvent = idClientEvent;
    }

    public Date getAlarmDate() {
        return AlarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        AlarmDate = alarmDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public Boolean getFlReminder() {
        return flReminder;
    }

    public void setFlReminder(Boolean flReminder) {
        this.flReminder = flReminder;
    }

    public Boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Boolean flStatus) {
        this.flStatus = flStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
