package com.framework.helix.service.impl;

import com.framework.helix.dao.ContactDao;
import com.framework.helix.entity.Contact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 5/28/2014.
 */
public class ContactServiceImpl implements ContactService{

    private ContactDao contactDao;

    @Autowired
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getContacts(int clientId) throws HelixServiceException {
        try {
            return contactDao.getContacts(clientId);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to get contacts.", e);
        }
    }

    public void deleteContact(int idContact) throws HelixServiceException{
        try {
            contactDao.deleteContact(idContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete contact details.", e);
        }
    }
    public void saveContact(Contact contact) throws HelixServiceException {
        try {
            contactDao.saveContact(contact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save contact.", e);
        }
    }

    public void updateContact(Contact contact) throws HelixServiceException {
        try {
            contactDao.updateContact(contact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update contact.", e);
        }
    }

    public void saveOrUpdateContact(Contact contact) throws HelixServiceException {
        try {
            contactDao.saveOrUpdateContact(contact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update contact.", e);
        }
    }

    public void deleteContact(Contact contact) throws HelixServiceException {
        try {
            contactDao.deleteContact(contact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete contact.", e);
        }
    }

    public Contact getContact(Integer idContact) throws HelixServiceException {
        try {
            return contactDao.getContact(idContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get contact.", e);
        }
    }

    public Contact getContact(Integer idContact, Boolean createInstanceIfNotExists) throws HelixServiceException {
        try {
            Contact contact = contactDao.getContact(idContact);
            if (createInstanceIfNotExists && contact == null) {
                contact = new Contact();
            }
            return contact;
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get contact.", e);
        }
    }

    public List<Contact> getContacts() throws HelixServiceException {
        try {
            return contactDao.getContacts();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get contacts.", e);
        }
    }

    public String getLastContactId() throws HelixServiceException {
        try {
            return contactDao.getLastContactId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get contactId.", e);
        }
    }


}

