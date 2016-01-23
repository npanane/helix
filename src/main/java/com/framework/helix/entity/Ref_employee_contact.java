package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */
@Entity
@Table(name = "ref_employee_contact")
public class Ref_employee_contact implements Serializable {

    @EmbeddedId
    private Ref_employee_contact_Ids ref_employee_contact_ids;

    @Column(name = "flStatus")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserCreated",referencedColumnName = "idUser")
    private User user;

    @Column(name = "DateCreated")
    private Date DateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUserLastUpdated",referencedColumnName = "idUser")
    private User idUserLastUpdated;

    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee" , referencedColumnName = "idEmployee" ,insertable = false, updatable = false )
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContact" , referencedColumnName = "idContact" ,insertable = false, updatable = false )
    private Contact contact;

    public Ref_employee_contact_Ids getRef_employee_contact_ids() {
        return ref_employee_contact_ids;
    }

    public void setRef_employee_contact_ids(Ref_employee_contact_Ids ref_employee_contact_ids) {
        this.ref_employee_contact_ids = ref_employee_contact_ids;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        DateUpdated = dateUpdated;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
