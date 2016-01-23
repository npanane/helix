package com.framework.helix.service.impl;

import com.framework.helix.dao.UserDao;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nuwan.n.bandara on 5/25/2014.
 */
public class UserServiceImpl implements UserService {

     private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String userName)throws HelixServiceException{
        try {
            return userDao.getUser(userName);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to get user.", e);
        }

    }

    public void updateUser(User user)throws HelixServiceException{
        try {
            userDao.updateUser(user);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to update user.", e);
        }

    }

}
