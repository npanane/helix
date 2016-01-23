package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientReferenceDao;
import com.framework.helix.entity.Clientreference;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientReferenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/4/2014.
 */
public class ClientReferenceServiceImpl implements ClientReferenceService{

    private ClientReferenceDao clientReferenceDao;

    @Autowired
    public void setClientReferenceDao(ClientReferenceDao clientReferenceDao) {
        this.clientReferenceDao = clientReferenceDao;
    }

    public void saveClientReference(Clientreference clientreference) throws HelixServiceException {
        try {
            clientReferenceDao.saveClientReference(clientreference);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save client reference.", e);
        }
    }

    public void updateClientReference(Clientreference clientreference) throws HelixServiceException {
        try {
            clientReferenceDao.updateClientReference(clientreference);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update client reference.", e);
        }
    }

    public void deleteClientReference(Clientreference clientreference) throws HelixServiceException {
        try {
            clientReferenceDao.deleteClientReference(clientreference);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete client reference.", e);
        }
    }

    public Clientreference getClientReference(Integer idClientreference) throws HelixServiceException {
        try {
            return clientReferenceDao.getClientReference(idClientreference);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client reference.", e);
        }
    }

    public List<Clientreference> getClientReferences() throws HelixServiceException {
        try {
            return clientReferenceDao.getClientReferences();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client references.", e);
        }
    }

    public String getLastClientReferenceId() throws HelixServiceException {
        try {
            return clientReferenceDao.getLastClientReferenceId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get clientReferenceId.", e);
        }
    }



}
