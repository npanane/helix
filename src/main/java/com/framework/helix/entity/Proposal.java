package com.framework.helix.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mahaiya on 10/27/2014.
 */
@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProposal")
    private Integer idProposal;

    @Column(name = "ContactPerson")
    private String ContactPerson;

    @Column(name = "Salutation")
    private String Salutation;

    @Column(name = "ResidentCount")
    private Integer ResidentCount;

    @Column(name = "BusinessCount")
    private Integer BusinessCount;

    @Column(name = "ZipCodes")
    private String ZipCodes;

    @Column(name = "AvgResponse")
    private Double AvgResponse;

    @Column(name = "AvgDonation")
    private Double AvgDonation;

    @Column(name = "POFee")
    private Double POFee;

    @Column(name = "MailPermitFee")
    private Double MailPermitFee;

    @Column(name = "Postage")
    private Double Postage;

    @Column(name = "DecalsFee")
    private Double DecalsFee;

    @Column(name = "DatabaseFee")
    private Double DatabaseFee;

    @Column(name = "DeliveryFee")
    private Double DeliveryFee;

    @Column(name = "AvgResponseSR")
    private Double AvgResponseSR;

    @Column(name = "AvgDonationSR")
    private Double AvgDonationSR;

    @Column(name = "PostageSR")
    private Double PostageSR;

    @Column(name = "DecalsFeeSR")
    private Double DecalsFeeSR;

    @Column(name = "DatabaseFeeSR")
    private Double DatabaseFeeSR;

    @Column(name = "DeliveryFeeSR")
    private Double DeliveryFeeSR;

    @Column(name = "Revenue")
    private Double Revenue;

    @Column(name = "Cost")
    private Double Cost;

    @Column(name = "Profit")
    private Double Profit;

    @Column(name = "SplitPercent")
    private Double SplitPercent;

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
    @JoinColumn(name = "idClient" )
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idAddress")
    private Address address;

    public Integer getIdProposal() {
        return idProposal;
    }

    public void setIdProposal(Integer idProposal) {
        this.idProposal = idProposal;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public Integer getResidentCount() {
        return ResidentCount;
    }

    public void setResidentCount(Integer residentCount) {
        ResidentCount = residentCount;
    }

    public String getSalutation() {
        return Salutation;
    }

    public void setSalutation(String salutation) {
        Salutation = salutation;
    }

    public String getZipCodes() {
        return ZipCodes;
    }

    public void setZipCodes(String zipCodes) {
        ZipCodes = zipCodes;
    }

    public Integer getBusinessCount() {
        return BusinessCount;
    }

    public void setBusinessCount(Integer businessCount) {
        BusinessCount = businessCount;
    }

    public Double getAvgResponse() {
        return AvgResponse;
    }

    public void setAvgResponse(Double avgResponse) {
        AvgResponse = avgResponse;
    }

    public Double getAvgDonation() {
        return AvgDonation;
    }

    public void setAvgDonation(Double avgDonation) {
        AvgDonation = avgDonation;
    }

    public Double getPOFee() {
        return POFee;
    }

    public void setPOFee(Double POFee) {
        this.POFee = POFee;
    }

    public Double getMailPermitFee() {
        return MailPermitFee;
    }

    public void setMailPermitFee(Double mailPermitFee) {
        MailPermitFee = mailPermitFee;
    }

    public Double getPostage() {
        return Postage;
    }

    public void setPostage(Double postage) {
        Postage = postage;
    }

    public Double getDecalsFee() {
        return DecalsFee;
    }

    public void setDecalsFee(Double decalsFee) {
        DecalsFee = decalsFee;
    }

    public Double getDatabaseFee() {
        return DatabaseFee;
    }

    public void setDatabaseFee(Double databaseFee) {
        DatabaseFee = databaseFee;
    }

    public Double getDeliveryFee() {
        return DeliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        DeliveryFee = deliveryFee;
    }

    public Double getAvgResponseSR() {
        return AvgResponseSR;
    }

    public void setAvgResponseSR(Double avgResponseSR) {
        AvgResponseSR = avgResponseSR;
    }

    public Double getAvgDonationSR() {
        return AvgDonationSR;
    }

    public void setAvgDonationSR(Double avgDonationSR) {
        AvgDonationSR = avgDonationSR;
    }

    public Double getPostageSR() {
        return PostageSR;
    }

    public void setPostageSR(Double postageSR) {
        PostageSR = postageSR;
    }

    public Double getDecalsFeeSR() {
        return DecalsFeeSR;
    }

    public void setDecalsFeeSR(Double decalsFeeSR) {
        DecalsFeeSR = decalsFeeSR;
    }

    public Double getDatabaseFeeSR() {
        return DatabaseFeeSR;
    }

    public void setDatabaseFeeSR(Double databaseFeeSR) {
        DatabaseFeeSR = databaseFeeSR;
    }

    public Double getDeliveryFeeSR() {
        return DeliveryFeeSR;
    }

    public void setDeliveryFeeSR(Double deliveryFeeSR) {
        DeliveryFeeSR = deliveryFeeSR;
    }

    public Double getRevenue() {
        return Revenue;
    }

    public void setRevenue(Double revenue) {
        Revenue = revenue;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    public Double getProfit() {
        return Profit;
    }

    public void setProfit(Double profit) {
        Profit = profit;
    }

    public Double getSplitPercent() {
        return SplitPercent;
    }

    public void setSplitPercent(Double splitPercent) {
        SplitPercent = splitPercent;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
