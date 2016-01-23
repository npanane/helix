package com.framework.helix.service;

import com.framework.helix.entity.Clientevent;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientEventsService {

    public void saveClientEvent(Clientevent clientevent) throws HelixServiceException;

    public List<Clientevent> getClientEvents(Integer idClient) throws HelixServiceException;

    public void deleteClientEvent(int idClientEvent) throws HelixServiceException;

    public Clientevent getClientEvent(int idClientEvent) throws HelixServiceException;

    public void updateClientEvent(Clientevent clientevent) throws HelixServiceException;

    public void saveOrUpdateClientEvent(Clientevent clientevent) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildNotesResponseXML(Integer rowId) throws HelixServiceException;
}
