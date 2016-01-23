package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser")
    private Integer idUser;

    @Column(name = "UserName")
    private String UserName;

    @Column(name = "Password")
    private String Password;

    @Column(name = "idRole")
    private Integer idRole;

    @Column(name = "flStatus")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @Column(name = "idUserCreated")
    private Integer idUserCreated;

    @Column(name = "DateCreated")
    private Date DateCreated;

    @Column(name = "idUserLastUpdated")
    private Integer idUserLastUpdated;

    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientevent> clientevents;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientselectioninstruction> clientselectioninstructions;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientcontact> clientcontacts;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<MasterCampaign> masterCampaign;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Personalcontact> personalcontacts;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Ref_employee_address> ref_employee_address;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Ref_employee_contact> ref_employee_contact;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<VendorContact> vendorContacts;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Vendor> vendors;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Vendortype> vendortypes;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Postoffice> postoffices;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Pickupmethod> pickupmethodses;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Renewtype> renewtypes;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Renewperiod> renewperiods;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Client> clients;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientstatus> clientstatus;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientpoboxcharge> clientpoboxcharges;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Clientreference> clientreferences;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Drive> drives;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Campaign> Campaign;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Barcode> barcodes;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Staffsuggestion> staffsuggestions;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Proposal> proposals;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
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

    public Integer getIdUserCreated() {
        return idUserCreated;
    }

    public void setIdUserCreated(Integer idUserCreated) {
        this.idUserCreated = idUserCreated;
    }

    public Integer getIdUserLastUpdated() {
        return idUserLastUpdated;
    }

    public void setIdUserLastUpdated(Integer idUserLastUpdated) {
        this.idUserLastUpdated = idUserLastUpdated;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Clientselectioninstruction> getClientselectioninstructions() {
        return clientselectioninstructions;
    }

    public void setClientselectioninstructions(List<Clientselectioninstruction> clientselectioninstructions) {
        this.clientselectioninstructions = clientselectioninstructions;
    }

    public List<Clientevent> getClientevents() {
        return clientevents;
    }

    public void setClientevents(List<Clientevent> clientevents) {
        this.clientevents = clientevents;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Clientcontact> getClientcontacts() {
        return clientcontacts;
    }

    public void setClientcontacts(List<Clientcontact> clientcontacts) {
        this.clientcontacts = clientcontacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<MasterCampaign> getMasterCampaign() {
        return masterCampaign;
    }

    public void setMasterCampaign(List<MasterCampaign> masterCampaign) {
        this.masterCampaign = masterCampaign;
    }

    public List<Personalcontact> getPersonalcontacts() {
        return personalcontacts;
    }

    public void setPersonalcontacts(List<Personalcontact> personalcontacts) {
        this.personalcontacts = personalcontacts;
    }

    public List<Ref_employee_address> getRef_employee_address() {
        return ref_employee_address;
    }

    public void setRef_employee_address(List<Ref_employee_address> ref_employee_address) {
        this.ref_employee_address = ref_employee_address;
    }

    public List<VendorContact> getVendorContacts() {
        return vendorContacts;
    }

    public void setVendorContacts(List<VendorContact> vendorContacts) {
        this.vendorContacts = vendorContacts;
    }

    public List<Ref_employee_contact> getRef_employee_contact() {
        return ref_employee_contact;
    }

    public void setRef_employee_contact(List<Ref_employee_contact> ref_employee_contact) {
        this.ref_employee_contact = ref_employee_contact;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public List<Vendortype> getVendortypes() {
        return vendortypes;
    }

    public void setVendortypes(List<Vendortype> vendortypes) {
        this.vendortypes = vendortypes;
    }

    public List<Postoffice> getPostoffices() {
        return postoffices;
    }

    public void setPostoffices(List<Postoffice> postoffices) {
        this.postoffices = postoffices;
    }

    public List<Pickupmethod> getPickupmethodses() {
        return pickupmethodses;
    }

    public void setPickupmethodses(List<Pickupmethod> pickupmethodses) {
        this.pickupmethodses = pickupmethodses;
    }

    public List<Renewtype> getRenewtypes() {
        return renewtypes;
    }

    public void setRenewtypes(List<Renewtype> renewtypes) {
        this.renewtypes = renewtypes;
    }

    public List<Renewperiod> getRenewperiods() {
        return renewperiods;
    }

    public void setRenewperiods(List<Renewperiod> renewperiods) {
        this.renewperiods = renewperiods;
    }

    public List<Clientstatus> getClientstatus() {
        return clientstatus;
    }

    public void setClientstatus(List<Clientstatus> clientstatus) {
        this.clientstatus = clientstatus;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
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

    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }

    public List<Campaign> getCampaign() {
        return Campaign;
    }

    public void setCampaign(List<Campaign> campaign) {
        Campaign = campaign;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    public List<Staffsuggestion> getStaffsuggestions() {
        return staffsuggestions;
    }

    public void setStaffsuggestions(List<Staffsuggestion> staffsuggestions) {
        this.staffsuggestions = staffsuggestions;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }
}
