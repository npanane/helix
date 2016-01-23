package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/8/2014.
 */

@Entity
@Table(name = "pickupmethod")
public class Pickupmethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPickupMethod")
    private Integer idPickupMethod;

    @Column(name = "PickupMethod")
    private String PickupMethod;

    @Column(name = "flStatus" )
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
    private User idUserLastUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpdated")
    private Date DateUpdated;

    @OneToMany(mappedBy = "pickupmethod",cascade=CascadeType.ALL)
    private List<Postoffice> postoffices;

    public Integer getIdPickupMethod() {
        return idPickupMethod;
    }

    public void setIdPickupMethod(Integer idPickupMethod) {
        this.idPickupMethod = idPickupMethod;
    }

    public String getPickupMethod() {
        return PickupMethod;
    }

    public void setPickupMethod(String pickupMethod) {
        PickupMethod = pickupMethod;
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

    public List<Postoffice> getPostoffices() {
        return postoffices;
    }

    public void setPostoffices(List<Postoffice> postoffices) {
        this.postoffices = postoffices;
    }
}
