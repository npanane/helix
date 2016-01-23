package com.framework.helix.service;

import com.framework.helix.entity.Pickupmethod;
import com.framework.helix.exception.HelixServiceException;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface PickUpMethodsService {

    public Pickupmethod getPickUpMethod(Integer idPickupMethod) throws HelixServiceException;

}
