package com.framework.helix.dao;

import com.framework.helix.entity.Clientcontact;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientContactsDao {

    public void saveClientContact(Clientcontact clientcontact) throws HelixDaoException;

    public List<Clientcontact> getClientContacts(Integer idClient) throws HelixDaoException;

    public void deleteClientContact(int idClientContact) throws HelixDaoException;

    public Clientcontact getClientContact(int idClientContact) throws HelixDaoException;

    public void updateClientContact(Clientcontact clientcontact) throws HelixDaoException;

    public Clientcontact getClientContactByName(String ContactName) throws HelixDaoException;
}
