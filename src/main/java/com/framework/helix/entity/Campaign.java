package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Mahaiya on 8/29/2014.
 */

@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "idCampaign")
    private Integer idCampaign;

    @Column(name = "CampaignYear")
    private String CampaignYear;

    @Column(name = "FormNo")
    private String FormNo;

    @Column(name = "BusinessMailed")
    private Integer BusinessMailed;

    @Column(name = "ResidentMailed")
    private Integer ResidentMailed;

    @Column(name = "CostOfLetters")
    private Double CostOfLetters;

    @Column(name = "CostOfData")
    private Double CostOfData;

    @Column(name = "CostOfPOBox")
    private Double CostOfPOBox;

    @Column(name = "CostOfDecals")
    private Double CostOfDecals;

    @Column(name = "CostOfProduction")
    private Double CostOfProduction;

    @Column(name = "CostOfEnvelopes")
    private Double CostOfEnvelopes;

    @Column(name = "CostOfArtwork")
    private Double CostOfArtwork;

    @Column(name = "CostOfPostage")
    private Double CostOfPostage;

    @Column(name = "CostOfPins")
    private Double CostOfPins;

    @Column(name = "flBulkRate" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flBulkRate;

    @Column(name = "flPhoto" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flPhoto;

    @Column(name = "flSignature" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flSignature;

    @Column(name = "flBadge" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flBadge;

    @Column(name = "flCauses" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCauses;

    @Column(name = "flDocLetter" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flDocLetter;

    @Column(name = "flDocRD" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flDocRD;

    @Column(name = "flDocCRM" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flDocCRM;

    @Column(name = "flCPLetter" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCPLetter;

    @Column(name = "flCPRD" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCPRD;


    @Column(name = "flCPBadge" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCPBadge;


    @Column(name = "flFPLetter" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flFPLetter;

    @Column(name = "flFPRD" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flFPRD;


    @Column(name = "flFPSticker" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flFPSticker;

    @Column(name = "flFPCRM")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flFPCRM;

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
    @JoinColumn(name = "idCampaignType" )
    private Campaigntype campaigntype;

    @ManyToOne
    @JoinColumn(name = "idClient" )
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idDrive" )
    private Drive drive;

    @OneToMany(mappedBy = "campaign",cascade=CascadeType.ALL)
    private List<Barcode> barcodes;


    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getFormNo() {
        return FormNo;
    }

    public void setFormNo(String formNo) {
        FormNo = formNo;
    }

    public String getCampaignYear() {
        return CampaignYear;
    }

    public void setCampaignYear(String campaignYear) {
        CampaignYear = campaignYear;
    }

    public Integer getBusinessMailed() {
        return BusinessMailed;
    }

    public void setBusinessMailed(Integer businessMailed) {
        BusinessMailed = businessMailed;
    }

    public Integer getResidentMailed() {
        return ResidentMailed;
    }

    public void setResidentMailed(Integer residentMailed) {
        ResidentMailed = residentMailed;
    }

    public Double getCostOfLetters() {
        return CostOfLetters;
    }

    public void setCostOfLetters(Double costOfLetters) {
        CostOfLetters = costOfLetters;
    }

    public Double getCostOfData() {
        return CostOfData;
    }

    public void setCostOfData(Double costOfData) {
        CostOfData = costOfData;
    }

    public Double getCostOfPOBox() {
        return CostOfPOBox;
    }

    public void setCostOfPOBox(Double costOfPOBox) {
        CostOfPOBox = costOfPOBox;
    }

    public Double getCostOfDecals() {
        return CostOfDecals;
    }

    public void setCostOfDecals(Double costOfDecals) {
        CostOfDecals = costOfDecals;
    }

    public Double getCostOfProduction() {
        return CostOfProduction;
    }

    public void setCostOfProduction(Double costOfProduction) {
        CostOfProduction = costOfProduction;
    }

    public Double getCostOfEnvelopes() {
        return CostOfEnvelopes;
    }

    public void setCostOfEnvelopes(Double costOfEnvelopes) {
        CostOfEnvelopes = costOfEnvelopes;
    }

    public Double getCostOfArtwork() {
        return CostOfArtwork;
    }

    public void setCostOfArtwork(Double costOfArtwork) {
        CostOfArtwork = costOfArtwork;
    }

    public Double getCostOfPostage() {
        return CostOfPostage;
    }

    public void setCostOfPostage(Double costOfPostage) {
        CostOfPostage = costOfPostage;
    }

    public Double getCostOfPins() {
        return CostOfPins;
    }

    public void setCostOfPins(Double costOfPins) {
        CostOfPins = costOfPins;
    }

    public Boolean getFlBulkRate() {
        return flBulkRate;
    }

    public void setFlBulkRate(Boolean flBulkRate) {
        this.flBulkRate = flBulkRate;
    }

    public Boolean getFlSignature() {
        return flSignature;
    }

    public void setFlSignature(Boolean flSignature) {
        this.flSignature = flSignature;
    }

    public Boolean getFlPhoto() {
        return flPhoto;
    }

    public void setFlPhoto(Boolean flPhoto) {
        this.flPhoto = flPhoto;
    }

    public Boolean getFlBadge() {
        return flBadge;
    }

    public void setFlBadge(Boolean flBadge) {
        this.flBadge = flBadge;
    }

    public Boolean getFlCauses() {
        return flCauses;
    }

    public void setFlCauses(Boolean flCauses) {
        this.flCauses = flCauses;
    }

    public Boolean getFlDocLetter() {
        return flDocLetter;
    }

    public void setFlDocLetter(Boolean flDocLetter) {
        this.flDocLetter = flDocLetter;
    }

    public Boolean getFlDocRD() {
        return flDocRD;
    }

    public void setFlDocRD(Boolean flDocRD) {
        this.flDocRD = flDocRD;
    }

    public Boolean getFlDocCRM() {
        return flDocCRM;
    }

    public void setFlDocCRM(Boolean flDocCRM) {
        this.flDocCRM = flDocCRM;
    }

    public Boolean getFlCPLetter() {
        return flCPLetter;
    }

    public void setFlCPLetter(Boolean flCPLetter) {
        this.flCPLetter = flCPLetter;
    }

    public Boolean getFlCPRD() {
        return flCPRD;
    }

    public void setFlCPRD(Boolean flCPRD) {
        this.flCPRD = flCPRD;
    }

    public Boolean getFlCPBadge() {
        return flCPBadge;
    }

    public void setFlCPBadge(Boolean flCPBadge) {
        this.flCPBadge = flCPBadge;
    }

    public Boolean getFlFPLetter() {
        return flFPLetter;
    }

    public void setFlFPLetter(Boolean flFPLetter) {
        this.flFPLetter = flFPLetter;
    }

    public Boolean getFlFPRD() {
        return flFPRD;
    }

    public void setFlFPRD(Boolean flFPRD) {
        this.flFPRD = flFPRD;
    }

    public Boolean getFlFPSticker() {
        return flFPSticker;
    }

    public void setFlFPSticker(Boolean flFPSticker) {
        this.flFPSticker = flFPSticker;
    }

    public Boolean getFlFPCRM() {
        return flFPCRM;
    }

    public void setFlFPCRM(Boolean flFPCRM) {
        this.flFPCRM = flFPCRM;
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

    public Campaigntype getCampaigntype() {
        return campaigntype;
    }

    public void setCampaigntype(Campaigntype campaigntype) {
        this.campaigntype = campaigntype;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
}
