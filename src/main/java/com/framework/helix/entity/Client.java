package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClient")
    private Integer idClient;

    @Column(name = "coClient")
    private String coClient;

    @Column(name = "ClientName")
    private String ClientName;

    @Column(name = "Jurisdiction")
    private String Jurisdiction;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CommencementDate")
    private Date CommencementDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TerminationDate")
    private Date TerminationDate;

    @Column(name = "TerminationBuffer")
    private Integer TerminationBuffer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CancellationDate")
    private Date CancellationDate;

    @Column(name = "flCancellationLetter" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCancellationLetter;

    @Column(name = "flCharity" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flCharity;

    @Column(name = "flTelemarketing" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flTelemarketing;

    @Column(name = "flHideContractAlert" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flHideContractAlert;

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
    @JoinColumn(name = "idAddressMailing", referencedColumnName = "idAddress" )
    private Address address;

    @ManyToOne
    @JoinColumn(name = "idContact" )
    private Contact contact;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Clientcontact> clientcontacts;

    /*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idClient")*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<MasterCampaign> masterCampaigns;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Clientevent> clientevents;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Clientselectioninstruction> clientselectioninstructions;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Postoffice> postoffices;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Noticeofintent> noticeofintents;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Clientupload> clientuploads;

    @ManyToOne
    @JoinColumn(name = "idRenewPeriod")
    private Renewperiod renewperiod;

    @ManyToOne
    @JoinColumn(name = "idRenewType")
    private Renewtype renewtype;

    @ManyToOne
    @JoinColumn(name = "idClientStatus")
    private Clientstatus clientstatus;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Clientpoboxcharge> clientpoboxcharges;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Clientreference> clientreferences;

    @ManyToOne
    @JoinColumn(name = "idSalesRep", referencedColumnName = "idEmployee")
    private Employee employee;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
    private List<Proposal> proposals;

    @ManyToOne
    @JoinColumn(name = "idClientType")
    private ClientType clientType;

    @Transient
    private Integer idClientType;

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getCoClient() {
        return coClient;
    }

    public void setCoClient(String coClient) {
        this.coClient = coClient;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getJurisdiction() {
        return Jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        Jurisdiction = jurisdiction;
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

    public List<Clientcontact> getClientcontacts() {
        return clientcontacts;
    }

    public void setClientcontacts(List<Clientcontact> clientcontacts) {
        this.clientcontacts = clientcontacts;
    }

    public List<MasterCampaign> getMasterCampaigns() {
        return masterCampaigns;
    }

    public void setMasterCampaigns(List<MasterCampaign> masterCampaigns) {
        this.masterCampaigns = masterCampaigns;
    }

    public List<Clientevent> getClientevents() {
        return clientevents;
    }

    public void setClientevents(List<Clientevent> clientevents) {
        this.clientevents = clientevents;
    }

    public List<Clientselectioninstruction> getClientselectioninstructions() {
        return clientselectioninstructions;
    }

    public void setClientselectioninstructions(List<Clientselectioninstruction> clientselectioninstructions) {
        this.clientselectioninstructions = clientselectioninstructions;
    }

    public List<Postoffice> getPostoffices() {
        return postoffices;
    }

    public void setPostoffices(List<Postoffice> postoffices) {
        this.postoffices = postoffices;
    }

    public Renewperiod getRenewperiod() {
        return renewperiod;
    }

    public void setRenewperiod(Renewperiod renewperiod) {
        this.renewperiod = renewperiod;
    }

    public Renewtype getRenewtype() {
        return renewtype;
    }

    public void setRenewtype(Renewtype renewtype) {
        this.renewtype = renewtype;
    }

    public Date getCommencementDate() {
        return CommencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        CommencementDate = commencementDate;
    }

    public Date getTerminationDate() {
        return TerminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        TerminationDate = terminationDate;
    }

    public Integer getTerminationBuffer() {
        return TerminationBuffer;
    }

    public void setTerminationBuffer(Integer terminationBuffer) {
        TerminationBuffer = terminationBuffer;
    }

    public Date getCancellationDate() {
        return CancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        CancellationDate = cancellationDate;
    }

    public Boolean getFlCancellationLetter() {
        return flCancellationLetter;
    }

    public void setFlCancellationLetter(Boolean flCancellationLetter) {
        this.flCancellationLetter = flCancellationLetter;
    }

    public Boolean getFlCharity() {
        return flCharity;
    }

    public void setFlCharity(Boolean flCharity) {
        this.flCharity = flCharity;
    }

    public Boolean getFlTelemarketing() {
        return flTelemarketing;
    }

    public void setFlTelemarketing(Boolean flTelemarketing) {
        this.flTelemarketing = flTelemarketing;
    }

    public Boolean getFlHideContractAlert() {
        return flHideContractAlert;
    }

    public void setFlHideContractAlert(Boolean flHideContractAlert) {
        this.flHideContractAlert = flHideContractAlert;
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

    public List<Noticeofintent> getNoticeofintents() {
        return noticeofintents;
    }

    public void setNoticeofintents(List<Noticeofintent> noticeofintents) {
        this.noticeofintents = noticeofintents;
    }

    public List<Clientupload> getClientuploads() {
        return clientuploads;
    }

    public void setClientuploads(List<Clientupload> clientuploads) {
        this.clientuploads = clientuploads;
    }

    public Clientstatus getClientstatus() {
        return clientstatus;
    }

    public void setClientstatus(Clientstatus clientstatus) {
        this.clientstatus = clientstatus;
    }

    public List<Clientpoboxcharge> getClientpoboxcharges() {
        return clientpoboxcharges;
    }

    public void setClientpoboxcharges(List<Clientpoboxcharge> clientpoboxcharges) {
        this.clientpoboxcharges = clientpoboxcharges;
    }

    public List<Clientreference> getClientreferences() {
        return clientreferences;
    }

    public void setClientreferences(List<Clientreference> clientreferences) {
        this.clientreferences = clientreferences;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Integer getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(Integer idClientType) {
        this.idClientType = idClientType;
    }
}
