package com.framework.helix.dao;

import com.framework.helix.entity.Renewtype;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface RenewTypesDao {

    public Renewtype getRenewTypes(Integer idRenewType) throws HelixDaoException;

    public List<Renewtype> getRenewTypes() throws HelixDaoException;
}
