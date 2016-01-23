package com.framework.helix.dao;

import com.framework.helix.entity.Postoffice;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/8/2014.
 */
public interface PostOfficeDao {

    public void deletePostOfficeDetails(int idPostoffice) throws HelixDaoException;

    public void savePostOfficeDetails(Postoffice postoffice) throws HelixDaoException;

    public void updatePostOfficeDetails(Postoffice postoffice) throws HelixDaoException;

    public void saveOrUpdatePostOfficeDetails(Postoffice postoffice) throws HelixDaoException;

    public void deletePostOfficeDetails(Postoffice postoffice) throws HelixDaoException;

    public Postoffice getPostOfficeDetails(Integer idPostoffice) throws HelixDaoException;

    public List<Postoffice> getPostOfficeDetails() throws HelixDaoException;

    public String getLastPostOfficeDetailsId() throws HelixDaoException;

    public List<Postoffice> getPostOfficeDetailsByClientId(Integer idClient) throws HelixDaoException;

    public List<Postoffice> getPostOfficeDetailsForDrivable(Integer idClient,String currentYear) throws HelixDaoException;

    public List<Postoffice> getPostOfficeDetailsForReShip(Integer idClient,String currentYear) throws HelixDaoException;

}
