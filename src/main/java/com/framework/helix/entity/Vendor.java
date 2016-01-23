package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/26/2014.
 */

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idVendor")
    private Integer idVendor;

    @Column(name = "VendorName")
    private String VendorName;

    @Column(name = "AccountNo")
    private String AccountNo;

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
    @JoinColumn(name = "idAddress")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "idVendorType")
    private Vendortype vendortype;

    @ManyToOne
    @JoinColumn(name = "idContact")
    private Contact contact;

    @OneToMany(mappedBy = "vendor",cascade=CascadeType.ALL)
    private List<VendorContact> vendorContacts;

    public Integer getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(Integer idVendor) {
        this.idVendor = idVendor;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Vendortype getVendortype() {
        return vendortype;
    }

    public void setVendortype(Vendortype vendortype) {
        this.vendortype = vendortype;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<VendorContact> getVendorContacts() {
        return vendorContacts;
    }

    public void setVendorContacts(List<VendorContact> vendorContacts) {
        this.vendorContacts = vendorContacts;
    }
}
