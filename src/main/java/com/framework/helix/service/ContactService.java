package com.framework.helix.service;

import com.framework.helix.entity.Contact;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/28/2014.
 */
public interface ContactService {

    public List<Contact> getContacts(int clientId)throws HelixServiceException;

    public void deleteContact(int idContact) throws HelixServiceException;

    public void saveContact(Contact contact) throws HelixServiceException;

    public void updateContact(Contact contact) throws HelixServiceException;

    public void saveOrUpdateContact(Contact contact) throws HelixServiceException;

    public void deleteContact(Contact contact) throws HelixServiceException;

    public Contact getContact(Integer idContact) throws HelixServiceException;

    public Contact getContact(Integer idContact, Boolean createInstanceIfNotExists) throws HelixServiceException;

    public List<Contact> getContacts() throws HelixServiceException;

    public String getLastContactId() throws HelixServiceException;
}
