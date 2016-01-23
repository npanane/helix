package com.framework.helix.service;

import com.framework.helix.entity.Postoffice;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/8/2014.
 */
public interface PostOfficeService {

    public void deletePostOfficeDetails(int idPostoffice) throws HelixServiceException;

    public void savePostOfficeDetails(Postoffice postoffice) throws HelixServiceException;

    public void updatePostOfficeDetails(Postoffice postoffice) throws HelixServiceException;

    public void saveOrUpdatePostOfficeDetails(Postoffice postoffice) throws HelixServiceException;

    public void deletePostOfficeDetails(Postoffice postoffice) throws HelixServiceException;

    public Postoffice getPostOfficeDetails(Integer idPostoffice) throws HelixServiceException;

    public List<Postoffice> getPostOfficeDetails() throws HelixServiceException;

    public String getLastPostOfficeDetailsId() throws HelixServiceException;

    public  List<Postoffice> getPostOfficeDetailsCI(Integer idClient) throws HelixServiceException;

    public List<Postoffice> getPostOfficeDetailsForDrivable(Integer idClient,String currentYear) throws HelixServiceException;

    public List<Postoffice> getPostOfficeDetailsForReShip(Integer idClient,String currentYear) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildPostOfficeDetailsResponseXML(Integer ClientId) throws HelixServiceException;

}
