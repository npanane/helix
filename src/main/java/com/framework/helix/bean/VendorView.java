package com.framework.helix.bean;

/**
 * Created with IntelliJ IDEA.
 * User: nuwan.n.panane
 * Date: 2/13/14
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class VendorView {
    private Integer contactId;
    private String firstName;
    private String lastName;
    private String title;
    private String primaryEmail;
    private String secondaryEmail;
    private String emailStatement;
    private Boolean faxStatement;
    private Boolean mailStatement;
    private String note;
    private Integer contactTypeId;
    private String accountNo;
    private String vendorType;

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

    public String getEmailStatement() {
        return emailStatement;
    }

    public void setEmailStatement(String emailStatement) {
        this.emailStatement = emailStatement;
    }

    public Boolean getFaxStatement() {
        return faxStatement;
    }

    public void setFaxStatement(Boolean faxStatement) {
        this.faxStatement = faxStatement;
    }

    public Boolean getMailStatement() {
        return mailStatement;
    }

    public void setMailStatement(Boolean mailStatement) {
        this.mailStatement = mailStatement;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Integer contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }
}
