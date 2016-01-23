package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/27/2014.
 */

@Entity
@Table(name = "vendorcontact")
public class VendorContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idVendorContact")
    private Integer idVendorContact;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Notes")
    private String Notes;

    @Column(name = "flStatus", nullable = false, columnDefinition = "tinyint(1) default 1")
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean flStatus = true;

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
    @JoinColumn(name = "idVendor")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public Integer getIdVendorContact() {
        return idVendorContact;
    }

    public void setIdVendorContact(Integer idVendorContact) {
        this.idVendorContact = idVendorContact;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(boolean flStatus) {
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
