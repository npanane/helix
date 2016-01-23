package com.framework.helix.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nuwan.n.bandara on 6/27/2014.
 */
@Embeddable
public class Ref_employee_address_Ids implements Serializable {

    @Column(name = "idEmployee")
    private Integer idEmployee;

    @Column(name = "idAddress")
    private Integer idAddress;
}
