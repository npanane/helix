package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 7/8/2014.
 */
@Entity
@Table(name = "postoffice")
public class Postoffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPostoffice")
    private Integer idPostoffice;

    @Column(name = "ContactPerson1")
    private String ContactPerson1;

    @Column(name = "ContactPerson2")
    private String ContactPerson2;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateLastPaid")
    private Date DateLastPaid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateExpiration")
    private Date DateExpiration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateLastCalled")
    private Date DateLastCalled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateLastPickup")
    private Date DateLastPickup;

    @Column(name = "RenewalPeriod")
    private Integer RenewalPeriod;

    @Column(name = "RenewalCost", precision = 10, scale = 0 )
    private BigDecimal RenewalCost;

    @Column(name = "BoxSize")
    private String BoxSize;

    @Column(name = "GhostNo")
    private String GhostNo;

    @Column(name = "flHideAlert" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flHideAlert;

    @Column(name = "Notes")
    private String Notes;

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

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idPickupMethod")
    private Pickupmethod pickupmethod;

    public Integer getIdPostoffice() {
        return idPostoffice;
    }

    public void setIdPostoffice(Integer idPostoffice) {
        this.idPostoffice = idPostoffice;
    }

    public String getContactPerson1() {
        return ContactPerson1;
    }

    public void setContactPerson1(String contactPerson1) {
        ContactPerson1 = contactPerson1;
    }

    public String getContactPerson2() {
        return ContactPerson2;
    }

    public void setContactPerson2(String contactPerson2) {
        ContactPerson2 = contactPerson2;
    }

    public Date getDateLastPaid() {
        return DateLastPaid;
    }

    public void setDateLastPaid(Date dateLastPaid) {
        DateLastPaid = dateLastPaid;
    }

    public Date getDateExpiration() {
        return DateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        DateExpiration = dateExpiration;
    }

    public Date getDateLastCalled() {
        return DateLastCalled;
    }

    public void setDateLastCalled(Date dateLastCalled) {
        DateLastCalled = dateLastCalled;
    }

    public Date getDateLastPickup() {
        return DateLastPickup;
    }

    public void setDateLastPickup(Date dateLastPickup) {
        DateLastPickup = dateLastPickup;
    }

    public Integer getRenewalPeriod() {
        return RenewalPeriod;
    }

    public void setRenewalPeriod(Integer renewalPeriod) {
        RenewalPeriod = renewalPeriod;
    }

    public BigDecimal getRenewalCost() {
        return RenewalCost;
    }

    public void setRenewalCost(BigDecimal renewalCost) {
        RenewalCost = renewalCost;
    }

    public String getBoxSize() {
        return BoxSize;
    }

    public void setBoxSize(String boxSize) {
        BoxSize = boxSize;
    }

    public String getGhostNo() {
        return GhostNo;
    }

    public void setGhostNo(String ghostNo) {
        GhostNo = ghostNo;
    }

    public Boolean getFlHideAlert() {
        return flHideAlert;
    }

    public void setFlHideAlert(Boolean flHideAlert) {
        this.flHideAlert = flHideAlert;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pickupmethod getPickupmethod() {
        return pickupmethod;
    }

    public void setPickupmethod(Pickupmethod pickupmethod) {
        this.pickupmethod = pickupmethod;
    }
}
