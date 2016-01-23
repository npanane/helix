package com.framework.helix.dao;

import com.framework.helix.entity.Personalcontact;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */

public interface PersonalContactDao {

    public void savePersonalContact(Personalcontact personalcontact)throws HelixDaoException;

    public void updatePersonalContact(Personalcontact personalcontact) throws HelixDaoException;

    public void saveOrUpdatePersonalContact(Personalcontact personalcontact) throws HelixDaoException;

    public void deletePersonalContact(Personalcontact personalcontact) throws HelixDaoException;

    public Personalcontact getPersonalContact(Integer idPersonalcontacts) throws HelixDaoException;

    public List<Personalcontact> getPersonalContact() throws HelixDaoException;

    public String getLastPersonalContactId() throws HelixDaoException;


}
