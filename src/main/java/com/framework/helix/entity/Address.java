package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/26/2014.
 */

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAddress")
    private Integer idAddress;

    @Column(name = "StreetName")
    private String StreetName;

    @Column(name = "AptUnitSuit")
    private String AptUnitSuit;

    @Column(name = "City")
    private String City;

    @Column(name = "ZipCode")
    private String ZipCode;

    @Column(name = "flStatus", insertable = false)
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

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Vendor> vendors;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Ref_employee_address> ref_employee_addresses;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Personalcontact> personalcontacts;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Client> clients;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Clientcontact> clientcontacts;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<MasterCampaign> masterCampaign;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Postoffice> postoffices;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Barcode> barcodes;

    @OneToMany(mappedBy = "address",cascade=CascadeType.ALL)
    private List<Proposal> proposals;

    @ManyToOne
    @JoinColumn(name = "idState" )
    private State state;


    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getAptUnitSuit() {
        return AptUnitSuit;
    }

    public void setAptUnitSuit(String aptUnitSuit) {
        AptUnitSuit = aptUnitSuit;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
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

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public List<Personalcontact> getPersonalcontacts() {
        return personalcontacts;
    }

    public void setPersonalcontacts(List<Personalcontact> personalcontacts) {
        this.personalcontacts = personalcontacts;
    }

    public List<Ref_employee_address> getRef_employee_addresses() {
        return ref_employee_addresses;
    }

    public void setRef_employee_addresses(List<Ref_employee_address> ref_employee_addresses) {
        this.ref_employee_addresses = ref_employee_addresses;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Clientcontact> getClientcontacts() {
        return clientcontacts;
    }

    public void setClientcontacts(List<Clientcontact> clientcontacts) {
        this.clientcontacts = clientcontacts;
    }

    public List<MasterCampaign> getMasterCampaign() {
        return masterCampaign;
    }

    public void setMasterCampaign(List<MasterCampaign> masterCampaign) {
        this.masterCampaign = masterCampaign;
    }

    public List<Postoffice> getPostoffices() {
        return postoffices;
    }

    public void setPostoffices(List<Postoffice> postoffices) {
        this.postoffices = postoffices;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }
}
