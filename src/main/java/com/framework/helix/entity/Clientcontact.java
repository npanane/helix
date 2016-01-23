package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */
@Entity
@Table(name = "clientcontact")
public class Clientcontact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClientContact")
    private Integer idClientContact;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Rank")
    private String Rank;

    @Column(name = "flMonthlyStatementByEmail")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flMonthlyStatementByEmail;

    @Column(name = "flMonthlyStatementByFax")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flMonthlyStatementByFax;

    @Column(name = "flMonthlyStatementByMail")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flMonthlyStatementByMail;

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

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;

    public Integer getIdClientContact() {
        return idClientContact;
    }

    public void setIdClientContact(Integer idClientContact) {
        this.idClientContact = idClientContact;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public Boolean getFlMonthlyStatementByEmail() {
        return flMonthlyStatementByEmail;
    }

    public void setFlMonthlyStatementByEmail(Boolean flMonthlyStatementByEmail) {
        this.flMonthlyStatementByEmail = flMonthlyStatementByEmail;
    }

    public Boolean getFlMonthlyStatementByFax() {
        return flMonthlyStatementByFax;
    }

    public void setFlMonthlyStatementByFax(Boolean flMonthlyStatementByFax) {
        this.flMonthlyStatementByFax = flMonthlyStatementByFax;
    }

    public Boolean getFlMonthlyStatementByMail() {
        return flMonthlyStatementByMail;
    }

    public void setFlMonthlyStatementByMail(Boolean flMonthlyStatementByMail) {
        this.flMonthlyStatementByMail = flMonthlyStatementByMail;
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

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
