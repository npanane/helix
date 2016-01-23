package com.framework.helix.service.impl;

import com.framework.helix.dao.DriveDao;
import com.framework.helix.entity.Drive;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 8/19/2014.
 */
public class DriveServiceImpl implements DriveService {

    private DriveDao driveDao;

    @Autowired
    public void setDriveDao(DriveDao driveDao) {
        this.driveDao = driveDao;
    }

    public void saveDrive(Drive drive) throws HelixServiceException {
        try {
            driveDao.saveDrive(drive);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save drive.", e);
        }
    }

    public void updateDrive(Drive drive) throws HelixServiceException {
        try {
            driveDao.updateDrive(drive);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update drive.", e);
        }
    }

    public void deleteDrive(Drive drive) throws HelixServiceException {
        try {
            driveDao.deleteDrive(drive);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete drive.", e);
        }
    }

    public Drive getDrive(Integer idDrive)  throws HelixServiceException {
        try {
            return driveDao.getDrive(idDrive);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get drive.", e);
        }
    }

    public List<Drive> getDrives() throws HelixServiceException {
        try {
            return driveDao.getDrives();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get drives.", e);
        }
    }

    public String getLastDriveId()  throws HelixServiceException {
        try {
            return driveDao.getLastDriveId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get driveId.", e);
        }
    }
}
