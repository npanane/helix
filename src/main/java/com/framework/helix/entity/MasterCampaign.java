package com.framework.helix.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */

@Entity
@Table(name = "mastercampaign")
public class MasterCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCampaign")
    private Integer idCampaign;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Rank")
    private String Rank;

    @Column(name = "TaxID")
    private String TaxID;

    @Column(name = "FriendsOf")
    private String FriendsOf;

    @Column(name = "DonationWebsite")
    private String DonationWebsite;

    @Column(name = "PictureTitle")
    private String PictureTitle;

    @Column(name = "BankAccountNo")
    private String BankAccountNo;

    @Column(name = "LocationNo")
    private String LocationNo;

    @Column(name = "Season")
    private String Season;

    @Column(name = "BaseFormNo")
    private String BaseFormNo;

    @Column(name = "LetterType")
    private String LetterType;

    @Column(name = "ClientPercent")
    private String ClientPercent;

    @Column(name = "flTaxDeductible")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flTaxDeductible;

    @Column(name = "NEPVoiceMail")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean NEPVoiceMail;


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

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "idAddress")
    private Address address;

    //@OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    //@PrimaryKeyJoinColumn
    //@JoinColumn(name = "idClient")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "idContact")
    private Contact contact;

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
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

    public String getTaxID() {
        return TaxID;
    }

    public void setTaxID(String taxID) {
        TaxID = taxID;
    }

    public String getFriendsOf() {
        return FriendsOf;
    }

    public void setFriendsOf(String friendsOf) {
        FriendsOf = friendsOf;
    }

    public String getPictureTitle() {
        return PictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        PictureTitle = pictureTitle;
    }

    public String getDonationWebsite() {
        return DonationWebsite;
    }

    public void setDonationWebsite(String donationWebsite) {
        DonationWebsite = donationWebsite;
    }

    public String getBankAccountNo() {
        return BankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        BankAccountNo = bankAccountNo;
    }

    public String getLocationNo() {
        return LocationNo;
    }

    public void setLocationNo(String locationNo) {
        LocationNo = locationNo;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public String getBaseFormNo() {
        return BaseFormNo;
    }

    public void setBaseFormNo(String baseFormNo) {
        BaseFormNo = baseFormNo;
    }

    public String getLetterType() {
        return LetterType;
    }

    public void setLetterType(String letterType) {
        LetterType = letterType;
    }

    public String getClientPercent() {
        return ClientPercent;
    }

    public void setClientPercent(String clientPercent) {
        if (StringUtils.isNotBlank(clientPercent)) {
            ClientPercent = clientPercent;
        }
        else {
            ClientPercent = "0";
        }
    }

    public Boolean getFlTaxDeductible() {
        return flTaxDeductible;
    }

    public void setFlTaxDeductible(Boolean flTaxDeductible) {
        this.flTaxDeductible = flTaxDeductible;
    }

    public Boolean getNEPVoiceMail() {
        return NEPVoiceMail;
    }

    public void setNEPVoiceMail(Boolean NEPVoiceMail) {
        this.NEPVoiceMail = NEPVoiceMail;
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

    public Boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Boolean flStatus) {
        this.flStatus = flStatus;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
