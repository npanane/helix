package com.framework.helix.dao;

import com.framework.helix.entity.Clientupload;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientUpLoadsDao {

    public void saveClientUpload(Clientupload clientupload) throws HelixDaoException;

    public List<Clientupload> getClientUploads(Integer idClient) throws HelixDaoException;

    public void deleteClientUpload(int idClientupload) throws HelixDaoException;

    public void updateClientUpload(Clientupload clientupload) throws HelixDaoException;


}
