package com.framework.helix.service;

import com.framework.helix.entity.Clientreference;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/4/2014.
 */
public interface ClientReferenceService {

    public void saveClientReference(Clientreference clientreference) throws HelixServiceException;

    public void updateClientReference(Clientreference clientreference) throws HelixServiceException;

    public void deleteClientReference(Clientreference clientreference) throws HelixServiceException;

    public Clientreference getClientReference(Integer idClientreference) throws HelixServiceException;

    public List<Clientreference> getClientReferences() throws HelixServiceException;

    public String getLastClientReferenceId() throws HelixServiceException;
}
