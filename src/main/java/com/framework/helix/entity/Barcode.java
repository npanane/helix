package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 9/1/2014.
 */
@Entity
@Table(name = "barcode")
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBarcode")
    private Integer idBarcode;

    @Column(name = "Barcode")
    private String Barcode;

    @Column(name = "ReferenceName1")
    private String ReferenceName1;

    @Column(name = "ReferenceName2")
    private String ReferenceName2;

    @Column(name = "DonationAmount1")
    private Double DonationAmount1;

    @Column(name = "DonationAmount2")
    private Double DonationAmount2;

    @Column(name = "DonationAmount3")
    private Double DonationAmount3;

    @Column(name = "DonationAmount4")
    private Double DonationAmount4;

    @Column(name = "DonationAmount5")
    private Double DonationAmount5;

    @Column(name = "GiftDescription1")
    private String GiftDescription1;

    @Column(name = "GiftDescription2")
    private String GiftDescription2;

    @Column(name = "GiftDescription3")
    private String GiftDescription3;

    @Column(name = "GiftDescription4")
    private String GiftDescription4;

    @Column(name = "GiftDescription5")
    private String GiftDescription5;

    @Column(name = "GiftDescription6")
    private String GiftDescription6;

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
    @JoinColumn(name = "idCampaign" )
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "idAddress" )
    private Address address;


    public Integer getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(Integer idBarcode) {
        this.idBarcode = idBarcode;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getReferenceName1() {
        return ReferenceName1;
    }

    public void setReferenceName1(String referenceName1) {
        ReferenceName1 = referenceName1;
    }

    public String getReferenceName2() {
        return ReferenceName2;
    }

    public void setReferenceName2(String referenceName2) {
        ReferenceName2 = referenceName2;
    }

    public Double getDonationAmount1() {
        return DonationAmount1;
    }

    public void setDonationAmount1(Double donationAmount1) {
        DonationAmount1 = donationAmount1;
    }

    public Double getDonationAmount2() {
        return DonationAmount2;
    }

    public void setDonationAmount2(Double donationAmount2) {
        DonationAmount2 = donationAmount2;
    }

    public Double getDonationAmount3() {
        return DonationAmount3;
    }

    public void setDonationAmount3(Double donationAmount3) {
        DonationAmount3 = donationAmount3;
    }

    public Double getDonationAmount4() {
        return DonationAmount4;
    }

    public void setDonationAmount4(Double donationAmount4) {
        DonationAmount4 = donationAmount4;
    }

    public Double getDonationAmount5() {
        return DonationAmount5;
    }

    public void setDonationAmount5(Double donationAmount5) {
        DonationAmount5 = donationAmount5;
    }

    public String getGiftDescription1() {
        return GiftDescription1;
    }

    public void setGiftDescription1(String giftDescription1) {
        GiftDescription1 = giftDescription1;
    }

    public String getGiftDescription2() {
        return GiftDescription2;
    }

    public void setGiftDescription2(String giftDescription2) {
        GiftDescription2 = giftDescription2;
    }

    public String getGiftDescription3() {
        return GiftDescription3;
    }

    public void setGiftDescription3(String giftDescription3) {
        GiftDescription3 = giftDescription3;
    }

    public String getGiftDescription4() {
        return GiftDescription4;
    }

    public void setGiftDescription4(String giftDescription4) {
        GiftDescription4 = giftDescription4;
    }

    public String getGiftDescription5() {
        return GiftDescription5;
    }

    public void setGiftDescription5(String giftDescription5) {
        GiftDescription5 = giftDescription5;
    }

    public String getGiftDescription6() {
        return GiftDescription6;
    }

    public void setGiftDescription6(String giftDescription6) {
        GiftDescription6 = giftDescription6;
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

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
