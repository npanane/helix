package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/26/2014.
 */
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idContact")
    private Integer idContact;

    @Column(name = "Main")
    private String Main;

    @Column(name = "MainExt")
    private String MainExt;

    @Column(name = "Home")
    private String Home;

    @Column(name = "Mobile")
    private String Mobile;

    @Column(name = "Work")
    private String Work;

    @Column(name = "WorkExt")
    private String WorkExt;

    @Column(name = "Fax")
    private String Fax;

    @Column(name = "Other")
    private String Other;

    @Column(name = "Dispatch")
    private String Dispatch;

    @Column(name = "Email")
    private String Email;

    @Column(name = "EmailAlt")
    private String EmailAlt;

    @Column(name = "WebURL")
    private String WebURL;

    @Column(name = "flStatus", nullable = false, columnDefinition = "int default 1")
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus = true;

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

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<Vendor> vendors;

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<VendorContact> vendorContacts;

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<Personalcontact> personalcontactses;

    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL)
    private List<Client> clients;

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<Clientcontact> clientcontacts;

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<MasterCampaign> masterCampaign;

    @OneToMany(mappedBy = "contact",cascade=CascadeType.ALL)
    private List<Postoffice> postoffices;

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getMain() {
        return Main;
    }

    public void setMain(String main) {
        Main = main;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getMainExt() {
        return MainExt;
    }

    public void setMainExt(String mainExt) {
        MainExt = mainExt;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public String getWorkExt() {
        return WorkExt;
    }

    public void setWorkExt(String workExt) {
        WorkExt = workExt;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public String getDispatch() {
        return Dispatch;
    }

    public void setDispatch(String dispatch) {
        Dispatch = dispatch;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmailAlt() {
        return EmailAlt;
    }

    public void setEmailAlt(String emailAlt) {
        EmailAlt = emailAlt;
    }

    public String getWebURL() {
        return WebURL;
    }

    public void setWebURL(String webURL) {
        WebURL = webURL;
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

    public List<VendorContact> getVendorContacts() {
        return vendorContacts;
    }

    public void setVendorContacts(List<VendorContact> vendorContacts) {
        this.vendorContacts = vendorContacts;
    }

    public List<Personalcontact> getPersonalcontactses() {
        return personalcontactses;
    }

    public void setPersonalcontactses(List<Personalcontact> personalcontactses) {
        this.personalcontactses = personalcontactses;
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
}
