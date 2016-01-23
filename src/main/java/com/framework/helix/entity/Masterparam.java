package com.framework.helix.entity;

import javax.persistence.*;

/**
 * Created by Mahaiya on 10/27/2014.
 */
@Entity
@Table(name = "masterparam")
public class Masterparam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMasterparam")
    private Integer idMasterparam;

    @Column(name = "POFee")
    private Double POFee;

    @Column(name = "MailPermitFee")
    private Double MailPermitFee;

    @Column(name = "AvgResponse")
    private Double AvgResponse;

    @Column(name = "AvgDonation")
    private Double AvgDonation;

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

    @Column(name = "BarCode")
    private String BarCode;

    public Integer getIdMasterparam() {
        return idMasterparam;
    }

    public void setIdMasterparam(Integer idMasterparam) {
        this.idMasterparam = idMasterparam;
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

    public Double getPostageSR() {
        return PostageSR;
    }

    public void setPostageSR(Double postageSR) {
        PostageSR = postageSR;
    }

    public Double getAvgDonationSR() {
        return AvgDonationSR;
    }

    public void setAvgDonationSR(Double avgDonationSR) {
        AvgDonationSR = avgDonationSR;
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

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}
