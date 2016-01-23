package com.framework.helix.service;

import com.framework.helix.entity.Client;
import com.framework.helix.entity.ClientType;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {

    public void saveClient(Client client) throws HelixServiceException;

    public void updateClient(Client client) throws HelixServiceException;

    public void saveOrUpdateClient(Client client) throws HelixServiceException;

    public void deleteClient(Client client) throws HelixServiceException;

    public Client getClient(Integer idClient) throws HelixServiceException;

    public List<Client> getClients() throws HelixServiceException;

    public List<Client> getClients(String status) throws HelixServiceException;

    public String getLastClientsId() throws HelixServiceException;

    public List<Client> getClientsForPoBoxPickup(String season, Integer year) throws HelixServiceException;

    public List<ClientType> getClientType() throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildClientResponseXML() throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildClientContactsResponseXML(Integer ClientId) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildClientAddressResponseXML(Integer ClientId) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildMailContractDetailsResponseXML(Integer ClientId) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildNewsResponseXML(Integer clientId) throws HelixServiceException;
}