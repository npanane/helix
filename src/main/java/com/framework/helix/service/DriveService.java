package com.framework.helix.service;

import com.framework.helix.entity.Drive;
import com.framework.helix.exception.HelixServiceException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 8/19/2014.
 */
public interface DriveService {

    public void saveDrive(Drive drive) throws HelixServiceException;

    public void updateDrive(Drive drive) throws HelixServiceException;

    public void deleteDrive(Drive drive) throws HelixServiceException;

    public Drive getDrive(Integer idDrive) throws HelixServiceException;

    public List<Drive> getDrives() throws HelixServiceException;

    public String getLastDriveId() throws HelixServiceException;

}
