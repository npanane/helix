package com.framework.helix.dao;

import com.framework.helix.entity.Clientevent;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface ClientEventsDao {

    public void saveClientEvent(Clientevent clientevent) throws HelixDaoException;

    public List<Clientevent> getClientEvents(Integer idClient) throws HelixDaoException;

    public void deleteClientEvent(int idClientEvent) throws HelixDaoException;

    public Clientevent getClientEvent(int idClientEvent) throws HelixDaoException;

    public void updateClientEvent(Clientevent clientevent) throws HelixDaoException;

    public void saveOrUpdateClientEvent(Clientevent clientevent) throws HelixDaoException;

}
