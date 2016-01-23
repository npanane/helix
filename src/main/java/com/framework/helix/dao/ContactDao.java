package com.framework.helix.dao;

import com.framework.helix.entity.Contact;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/28/2014.
 */
public interface ContactDao {

    public List<Contact> getContacts(int clientId) throws HelixDaoException;

    public void deleteContact(int idContact) throws HelixDaoException;

    public void saveContact(Contact contact)throws HelixDaoException;

    public void updateContact(Contact contact) throws HelixDaoException;

    public void saveOrUpdateContact(Contact contact) throws HelixDaoException;

    public void deleteContact(Contact contact) throws HelixDaoException;

    public Contact getContact(Integer idContact) throws HelixDaoException;

    public List<Contact> getContacts() throws HelixDaoException;

    public String getLastContactId() throws HelixDaoException;
}
