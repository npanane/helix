package com.framework.helix.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Nuwan on 6/30/2014.
 */
@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idState")
    private Integer idState;

    @Column(name = "abbrState")
    private String abbrState;

    @Column(name = "State")
    private String state;

    @Column(name = "flStatus")
    private Integer flStatus;

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getAbbrState() {
        return abbrState;
    }

    public void setAbbrState(String abbrState) {
        this.abbrState = abbrState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(Integer flStatus) {
        this.flStatus = flStatus;
    }
}
