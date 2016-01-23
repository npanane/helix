package com.framework.helix.dao;

import com.framework.helix.entity.Clientreference;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/4/2014.
 */
public interface ClientReferenceDao {

    public void saveClientReference(Clientreference clientreference) throws HelixDaoException;

    public void updateClientReference(Clientreference clientreference) throws HelixDaoException;

    public void deleteClientReference(Clientreference clientreference) throws HelixDaoException;

    public Clientreference getClientReference(Integer idClientreference) throws HelixDaoException;

    public List<Clientreference> getClientReferences() throws HelixDaoException;

    public String getLastClientReferenceId() throws HelixDaoException;
}
