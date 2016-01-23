package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientStatusDao;
import com.framework.helix.entity.Clientstatus;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientStatusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nuwan.n.bandara on 8/20/2014.
 */
public class ClientStatusServiceImpl implements ClientStatusService {

    private ClientStatusDao clientStatusDao;

    @Autowired
    public void setClientStatusDao(ClientStatusDao clientStatusDao) {
        this.clientStatusDao = clientStatusDao;
    }

    public Clientstatus getClientStatus(Integer idClientStatus) throws HelixServiceException {
        try {
            return clientStatusDao.getClientStatus(idClientStatus);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client status.", e);
        }
    }

}
