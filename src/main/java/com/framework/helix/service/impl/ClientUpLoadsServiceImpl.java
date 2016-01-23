package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientUpLoadsDao;
import com.framework.helix.entity.Clientupload;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientUpLoadsService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public class ClientUpLoadsServiceImpl implements ClientUpLoadsService {

    private ClientUpLoadsDao clientUpLoadsDao;

    @Autowired
    public void setClientUpLoadsDao(ClientUpLoadsDao clientUpLoadsDao) {
        this.clientUpLoadsDao = clientUpLoadsDao;
    }

    public void saveClientUpload(Clientupload clientupload)  throws HelixServiceException {
        try {
            clientUpLoadsDao.saveClientUpload(clientupload);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save client upload.", e);
        }
    }

    public List<Clientupload> getClientUploads(Integer idClient) throws HelixServiceException {
        try {
            return clientUpLoadsDao.getClientUploads(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client uploads.", e);
        }
    }

    public void deleteClientUpload(int idClientupload) throws HelixServiceException {
        try {
            clientUpLoadsDao.deleteClientUpload(idClientupload);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete clients upload file.", e);
        }
    }

    public void updateClientUpload(Clientupload clientupload) throws HelixServiceException {
        try {
            clientUpLoadsDao.updateClientUpload(clientupload);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update client upload.", e);
        }
    }

    public Document buildUploadFilesResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("uploadItems");
        List<Clientupload> clientUploadsList = getClientUploads(rowId);
        if (clientUploadsList!=null) {
            for (Clientupload clientUpload : clientUploadsList) {
                Element element = root.addElement("uploadItem");
                element.addAttribute("idClientupload", String.valueOf(clientUpload.getIdClientupload()));
                if(clientUpload.getDateCreated()!=null){
                    element.addAttribute("date", clientUpload.getDateCreated().toString().substring(0, 10));}
                element.addAttribute("by", clientUpload.getUser().getUserName());
                element.addAttribute("fileName",clientUpload.getFileName() );
            }
        }
        return document;
    }
}
