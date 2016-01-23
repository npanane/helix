package com.framework.helix.bean;

/**
 * Created with IntelliJ IDEA.
 * User: nuwan.n.panane
 * Date: 2/13/14
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContactView {
    private Integer contactId;
    private String firstName;
    private String lastName;
    private String title;
    private String primaryEmail;
    private String secondaryEmail;
    private String note;
    private String officePhoneNo;
    private String homePhoneNo;
    private String mobileNo;
    private String mainPhoneNo;
    private String workFaxNo;
    private String homeFaxNo;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOfficePhoneNo() {
        return officePhoneNo;
    }

    public void setOfficePhoneNo(String officePhoneNo) {
        this.officePhoneNo = officePhoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMainPhoneNo() {
        return mainPhoneNo;
    }

    public void setMainPhoneNo(String mainPhoneNo) {
        this.mainPhoneNo = mainPhoneNo;
    }

    public String getWorkFaxNo() {
        return workFaxNo;
    }

    public void setWorkFaxNo(String workFaxNo) {
        this.workFaxNo = workFaxNo;
    }

    public String getHomeFaxNo() {
        return homeFaxNo;
    }

    public void setHomeFaxNo(String homeFaxNo) {
        this.homeFaxNo = homeFaxNo;
    }
}
