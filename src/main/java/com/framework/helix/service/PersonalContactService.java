package com.framework.helix.service;

import com.framework.helix.entity.Personalcontact;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/29/2014.
 */
public interface PersonalContactService {

    public void savePersonalContact(Personalcontact personalcontact) throws HelixServiceException;

    public void updatePersonalContact(Personalcontact personalcontact) throws HelixServiceException;

    public void saveOrUpdatePersonalContact(Personalcontact personalcontact) throws HelixServiceException;

    public void deletePersonalContact(Personalcontact personalcontact) throws HelixServiceException;

    public Personalcontact getPersonalContact(Integer idPersonalcontacts) throws HelixServiceException;

    public List<Personalcontact> getPersonalContact() throws HelixServiceException;

    public String getLastPersonalContactId() throws HelixServiceException;

}
