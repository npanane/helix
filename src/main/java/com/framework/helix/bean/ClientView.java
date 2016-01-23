package com.framework.helix.bean;

import com.framework.helix.entity.Address;
import com.framework.helix.entity.Contact;


import java.util.List;

public class ClientView {

    private Integer clientId;
    private String name;
    private String acronym;
    private String jurisdiction;
    private List<Address> addresses;
    private List<Contact> contacts;
    private Boolean yearBook;
    private Boolean sfp;
    private String mainPhone;
    private String workPhone;
    private String mobilePhone;
    // vendor info
    private String taxId;
    private String friendsOf; // todo: this needs to come from contact drop down list.
    private String clientWebsite;
    private String donationWebsite;
    private String pictureTitle;
    private String bankAccountNo;
    private String locationNo;
    private Boolean nepVoicemail;
    private Integer seasonId;
    private String baseFormNo;
    private String letterType;
    private Double clientPercentage;
    private Double sfpPercentage;
    private Boolean taxDeductible;
    private String tmpInput;

    private Integer RepId;
    private String RepName;

    public Integer getRepId() {
        return RepId;
    }

    public void setRepId(Integer repId) {
        RepId = repId;
    }

    public String getRepName() {
        return RepName;
    }

    public void setRepName(String repName) {
        RepName = repName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }


    public Boolean getYearBook() {
        return yearBook;
    }

    public void setYearBook(Boolean yearBook) {
        this.yearBook = yearBook;
    }

    public Boolean getSfp() {
        return sfp;
    }

    public void setSfp(Boolean sfp) {
        this.sfp = sfp;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getFriendsOf() {
        return friendsOf;
    }

    public void setFriendsOf(String friendsOf) {
        this.friendsOf = friendsOf;
    }

    public String getClientWebsite() {
        return clientWebsite;
    }

    public void setClientWebsite(String clientWebsite) {
        this.clientWebsite = clientWebsite;
    }

    public String getDonationWebsite() {
        return donationWebsite;
    }

    public void setDonationWebsite(String donationWebsite) {
        this.donationWebsite = donationWebsite;
    }

    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public Boolean getNepVoicemail() {
        return nepVoicemail;
    }

    public void setNepVoicemail(Boolean nepVoicemail) {
        this.nepVoicemail = nepVoicemail;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getBaseFormNo() {
        return baseFormNo;
    }

    public void setBaseFormNo(String baseFormNo) {
        this.baseFormNo = baseFormNo;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public Double getClientPercentage() {
        return clientPercentage;
    }

    public void setClientPercentage(Double clientPercentage) {
        this.clientPercentage = clientPercentage;
    }

    public Double getSfpPercentage() {
        return sfpPercentage;
    }

    public void setSfpPercentage(Double sfpPercentage) {
        this.sfpPercentage = sfpPercentage;
    }

    public Boolean getTaxDeductible() {
        return taxDeductible;
    }

    public void setTaxDeductible(Boolean taxDeductible) {
        this.taxDeductible = taxDeductible;
    }

    public String getTmpInput() {
        return tmpInput;
    }

    public void setTmpInput(String tmpInput) {
        this.tmpInput = tmpInput;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
