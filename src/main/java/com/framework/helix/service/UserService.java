package com.framework.helix.service;

/**
 * Created by nuwan.n.bandara on 5/25/2014.
 */
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;

public interface UserService {

    public User getUser(String userName) throws HelixServiceException;

    public void updateUser(User user) throws HelixServiceException;



}
