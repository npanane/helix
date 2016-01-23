package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientEventsDao;
import com.framework.helix.entity.Clientevent;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientEventsService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public class ClientEventsServiceImpl implements ClientEventsService{

    private static final String dfs = "MM-dd-yyyy";
    private static DateFormat df = new SimpleDateFormat(dfs);

    private ClientEventsDao clientEventsDao;

    @Autowired
    public void setClientEventsDao(ClientEventsDao clientEventsDao) {
        this.clientEventsDao = clientEventsDao;
    }

    public void saveClientEvent(Clientevent clientevent)  throws HelixServiceException {
        try {
            clientEventsDao.saveClientEvent(clientevent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save client event.", e);
        }
    }

    public List<Clientevent> getClientEvents(Integer idClient)throws HelixServiceException {
        try {
            return clientEventsDao.getClientEvents(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client event.", e);
        }
    }

    public void deleteClientEvent(int idClientEvent) throws HelixServiceException {
        try {
            clientEventsDao.deleteClientEvent(idClientEvent);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete client event details.", e);
        }
    }

    public void updateClientEvent(Clientevent clientevent) throws HelixServiceException {
        try {
            clientEventsDao.updateClientEvent(clientevent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update client event.", e);
        }
    }

    public void saveOrUpdateClientEvent(Clientevent clientevent) throws HelixServiceException {
        try {
            clientEventsDao.saveOrUpdateClientEvent(clientevent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update client event.", e);
        }
    }

    public Clientevent getClientEvent(int idClientEvent) throws HelixServiceException {
        try {
            return clientEventsDao.getClientEvent(idClientEvent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client event.", e);
        }
    }

    public Document buildNotesResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("notes");
        List<Clientevent> clientEventsList = getClientEvents(rowId);
        if (clientEventsList!=null) {
            for (Clientevent clientEvent : clientEventsList) {
                Element element = root.addElement("note");
                element.addAttribute("noteId", String.valueOf(clientEvent.getIdClientEvent()));
                element.addAttribute("date", ((clientEvent.getDateUpdated() != null)
                        ? df.format(clientEvent.getDateUpdated()) : ""));
                element.addAttribute("by", clientEvent.getUser().getEmployee().getFirstName());
                element.addAttribute("note", clientEvent.getNotes());
            }
        }
        return document;
    }

}
