package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/1/2014.
 */
@Entity
@Table(name = "campaigntype")
public class Campaigntype {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCampaignType")
    private Integer idCampaignType;

    @Column(name = "CampaignType")
    private String CampaignType;

    @Column(name = "flStatus" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated")
    private Date DateCreated;

    @OneToMany(mappedBy = "campaigntype",cascade=CascadeType.ALL)
    private List<Campaign> campaigns;

    public Integer getIdCampaignType() {
        return idCampaignType;
    }

    public void setIdCampaignType(Integer idCampaignType) {
        this.idCampaignType = idCampaignType;
    }

    public String getCampaignType() {
        return CampaignType;
    }

    public void setCampaignType(String campaignType) {
        CampaignType = campaignType;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    public Boolean getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Boolean flStatus) {
        this.flStatus = flStatus;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
