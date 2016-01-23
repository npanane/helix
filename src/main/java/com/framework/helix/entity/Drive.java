package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 8/19/2014.
 */

@Entity
@Table(name = "drive")
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDrive")
    private Integer idDrive;

    @Column(name = "coDrive")
    private String coDrive;

    @Column(name = "DriveName")
    private String DriveName;

    @Column(name = "FormNoExt")
    private String FormNoExt;

    @Column(name = "DriveMonth")
    private Integer DriveMonth;

    @Column(name = "DriveYear")
    private Integer DriveYear;

    @Column(name = "flNoResponses" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flNoResponses;

    @Column(name = "flInProcess" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flInProcess;

    @Column(name = "flSFP" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flSFP;

    @Column(name = "flPOBoxFee" )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean flPOBoxFee;

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

    @OneToMany(mappedBy = "drive",cascade=CascadeType.ALL)
    private List<Campaign> campaigns;

    public Integer getIdDrive() {
        return idDrive;
    }

    public void setIdDrive(Integer idDrive) {
        this.idDrive = idDrive;
    }

    public String getCoDrive() {
        return coDrive;
    }

    public void setCoDrive(String coDrive) {
        this.coDrive = coDrive;
    }

    public String getDriveName() {
        return DriveName;
    }

    public void setDriveName(String driveName) {
        DriveName = driveName;
    }

    public Integer getDriveMonth() {
        return DriveMonth;
    }

    public void setDriveMonth(Integer driveMonth) {
        DriveMonth = driveMonth;
    }

    public Integer getDriveYear() {
        return DriveYear;
    }

    public void setDriveYear(Integer driveYear) {
        DriveYear = driveYear;
    }

    public Boolean getFlNoResponses() {
        return flNoResponses;
    }

    public void setFlNoResponses(Boolean flNoResponses) {
        this.flNoResponses = flNoResponses;
    }

    public Boolean getFlInProcess() {
        return flInProcess;
    }

    public void setFlInProcess(Boolean flInProcess) {
        this.flInProcess = flInProcess;
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

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public String getFormNoExt() {
        return FormNoExt;
    }

    public void setFormNoExt(String formNoExt) {
        FormNoExt = formNoExt;
    }

    public Boolean getFlSFP() {
        return flSFP;
    }

    public void setFlSFP(Boolean flSFP) {
        this.flSFP = flSFP;
    }

    public Boolean getFlPOBoxFee() {
        return flPOBoxFee;
    }

    public void setFlPOBoxFee(Boolean flPOBoxFee) {
        this.flPOBoxFee = flPOBoxFee;
    }


}
