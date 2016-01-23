package com.framework.helix.service;

import com.framework.helix.entity.Renewtype;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface RenewTypesService {

    public Renewtype getRenewTypes(Integer idRenewType) throws HelixServiceException;

    public List<Renewtype> getRenewTypes() throws HelixServiceException;
}
