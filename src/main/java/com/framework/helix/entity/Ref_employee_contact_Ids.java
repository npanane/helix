package com.framework.helix.entity;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by nuwan.n.bandara on 6/30/2014.
 */
public class Ref_employee_contact_Ids implements Serializable {

    @Column(name = "idEmployee")
    private Integer idEmployee;

    @Column(name = "idContact")
    private Integer idContact;
}
