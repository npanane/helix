package com.framework.helix.service;

import com.framework.helix.entity.Clientupload;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientUpLoadsService {


    public void saveClientUpload(Clientupload clientupload) throws HelixServiceException;

    public List<Clientupload> getClientUploads(Integer idClient) throws HelixServiceException;

    public void deleteClientUpload(int idClientupload) throws HelixServiceException;

    public void updateClientUpload(Clientupload clientupload) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildUploadFilesResponseXML(Integer rowId) throws HelixServiceException;
}
