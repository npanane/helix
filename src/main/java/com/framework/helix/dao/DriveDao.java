package com.framework.helix.dao;

import com.framework.helix.entity.Drive;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 8/19/2014.
 */
public interface DriveDao {

    public void saveDrive(Drive drive) throws HelixDaoException;

    public void updateDrive(Drive drive) throws HelixDaoException;

    public void deleteDrive(Drive drive) throws HelixDaoException;

    public Drive getDrive(Integer idDrive) throws HelixDaoException;

    public List<Drive> getDrives() throws HelixDaoException;

    public String getLastDriveId() throws HelixDaoException;
}
