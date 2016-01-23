package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */
@Entity
@Table(name = "personalcontact")
public class Personalcontact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPersonalcontact")
    private Integer idPersonalcontact;

    @Column(name = "BusinessName")
    private String BusinessName;

    @Column(name = "ContactPerson")
    private String ContactPerson;


    @Column(name = "Notes")
    private String Notes;

    @Column(name = "flStatus")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @ManyToOne
    @JoinColumn(name = "idUserCreated",referencedColumnName = "idUser")
    private User user;

    @Column(name = "DateCreated")
    private Date DateCreated;

    @ManyToOne
    @JoinColumn(name = "idUserLastUpdated",referencedColumnName = "idUser")
    private User idUserLastUpdated;

    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    public Integer getIdPersonalcontact() {
        return idPersonalcontact;
    }

    public void setIdPersonalcontact(Integer idPersonalcontact) {
        this.idPersonalcontact = idPersonalcontact;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }
}
