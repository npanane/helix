package com.framework.helix.service;

import com.framework.helix.entity.Clientcontact;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientContactsService {

    public void saveClientContact(Clientcontact clientcontact) throws HelixServiceException;

    public List<Clientcontact> getClientContacts(Integer idClient) throws HelixServiceException;

    public void deleteClientContact(int idClientContact) throws HelixServiceException;

    public Clientcontact getClientContact(int idClientContact) throws HelixServiceException;

    public void updateClientContact(Clientcontact clientcontact) throws HelixServiceException;

    public Clientcontact getClientContactByName(String ContactName) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildContactsResponseXML(Integer rowId) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildSelectedClientContactResponseXML(Integer rowId) throws HelixServiceException;
}
