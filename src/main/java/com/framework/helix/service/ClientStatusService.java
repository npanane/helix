package com.framework.helix.service;

import com.framework.helix.entity.Clientstatus;
import com.framework.helix.exception.HelixServiceException;

/**
 * Created by nuwan.n.bandara on 8/20/2014.
 */
public interface ClientStatusService {

    public Clientstatus getClientStatus(Integer idClientStatus) throws HelixServiceException;
}
