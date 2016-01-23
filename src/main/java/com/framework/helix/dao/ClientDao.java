package com.framework.helix.dao;

import com.framework.helix.entity.Client;
import com.framework.helix.entity.ClientType;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

public interface ClientDao {

    public void saveClient(Client client)throws HelixDaoException;

    public void updateClient(Client client) throws HelixDaoException;

    public void saveOrUpdateClient(Client client) throws HelixDaoException;

    public void deleteClient(Client client) throws HelixDaoException;

    public Client getClient(Integer idClient) throws HelixDaoException;

    public List<Client> getClients() throws HelixDaoException;

    public List<Client> getClients(String status) throws HelixDaoException;

    public String getLastClientsId() throws HelixDaoException;

    public List<Client> getClientsForPoBoxPickup(String season, Integer year) throws HelixDaoException;

    public List<ClientType> getClientType() throws HelixDaoException;
}