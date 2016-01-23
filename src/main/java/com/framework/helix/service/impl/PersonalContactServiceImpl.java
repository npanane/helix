package com.framework.helix.service.impl;

import com.framework.helix.dao.PersonalContactDao;
import com.framework.helix.entity.Personalcontact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.PersonalContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */
public class PersonalContactServiceImpl implements PersonalContactService {

    private PersonalContactDao personalContactDao;

    @Autowired
    public void setPersonalContactDao(PersonalContactDao personalContactDao) {
        this.personalContactDao = personalContactDao;
    }

    public void savePersonalContact(Personalcontact personalcontact) throws HelixServiceException {
        try {
            personalContactDao.savePersonalContact(personalcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to personal contact client.", e);
        }
    }

    public void updatePersonalContact(Personalcontact personalcontact) throws HelixServiceException {
        try {
            personalContactDao.updatePersonalContact(personalcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update personal contact.", e);
        }
    }

    public void saveOrUpdatePersonalContact(Personalcontact personalcontact) throws HelixServiceException {
        try {
            personalContactDao.saveOrUpdatePersonalContact(personalcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update personal contact.", e);
        }
    }

    public void deletePersonalContact(Personalcontact personalcontact) throws HelixServiceException {
        try {
            personalContactDao.deletePersonalContact(personalcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete personal contact.", e);
        }
    }

    public Personalcontact getPersonalContact(Integer idPersonalcontacts) throws HelixServiceException {
        try {
            return personalContactDao.getPersonalContact(idPersonalcontacts);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get personal contact.", e);
        }
    }

    public List<Personalcontact> getPersonalContact() throws HelixServiceException {
        try {
            return personalContactDao.getPersonalContact();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get personal contacts.", e);
        }
    }

    public String getLastPersonalContactId() throws HelixServiceException {
        try {
            return personalContactDao.getLastPersonalContactId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get personal contact Id.", e);
        }
    }
}
