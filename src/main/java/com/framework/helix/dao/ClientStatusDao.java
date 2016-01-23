package com.framework.helix.dao;

import com.framework.helix.entity.Clientstatus;
import com.framework.helix.exception.HelixDaoException;

/**
 * Created by nuwan.n.bandara on 8/20/2014.
 */
public interface ClientStatusDao {

    public Clientstatus getClientStatus(Integer idClientStatus) throws HelixDaoException;
}
