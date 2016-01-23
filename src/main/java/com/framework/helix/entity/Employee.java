package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 6/26/2014.
 */

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEmployee")
    private Integer idEmployee;

    @Column(name = "coEmployee")
    private String coEmployee;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Gender")
    private String Gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOB")
    private Date DOB;

    @Column(name = "Title")
    private String Title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "JoinDate")
    private Date JoinDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastAccessDate")
    private Date LastAccessDate;

    @Column(name = "LastAccessPage")
    private String LastAccessPage;

    @Column(name = "LastAccessIP")
    private String LastAccessIP;

    @Column(name = "flStatus")
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
    private User userLastUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL)
    private List<Ref_employee_address> ref_employee_addresses;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL)
    private List<Personalcontact> personalcontactses;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL)
    private List<User> userList;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL)
    private List<Ref_employee_contact> ref_employee_contacts;

    @OneToMany(mappedBy = "employee",cascade=CascadeType.ALL)
    private List<Client> clients;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCoEmployee() {
        return coEmployee;
    }

    public void setCoEmployee(String coEmployee) {
        this.coEmployee = coEmployee;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(Date joinDate) {
        JoinDate = joinDate;
    }

    public Date getLastAccessDate() {
        return LastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        LastAccessDate = lastAccessDate;
    }

    public String getLastAccessPage() {
        return LastAccessPage;
    }

    public void setLastAccessPage(String lastAccessPage) {
        LastAccessPage = lastAccessPage;
    }

    public String getLastAccessIP() {
        return LastAccessIP;
    }

    public void setLastAccessIP(String lastAccessIP) {
        LastAccessIP = lastAccessIP;
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

    public User getUserLastUpdated() {
        return userLastUpdated;
    }

    public void setUserLastUpdated(User userLastUpdated) {
        this.userLastUpdated = userLastUpdated;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }

    public List<Ref_employee_address> getRef_employee_addresses() {
        return ref_employee_addresses;
    }

    public void setRef_employee_addresses(List<Ref_employee_address> ref_employee_addresses) {
        this.ref_employee_addresses = ref_employee_addresses;
    }

    public List<Personalcontact> getPersonalcontactses() {
        return personalcontactses;
    }

    public void setPersonalcontactses(List<Personalcontact> personalcontactses) {
        this.personalcontactses = personalcontactses;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Ref_employee_contact> getRef_employee_contacts() {
        return ref_employee_contacts;
    }

    public void setRef_employee_contacts(List<Ref_employee_contact> ref_employee_contacts) {
        this.ref_employee_contacts = ref_employee_contacts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
