package com.framework.helix.dao;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixDaoException;

/**
 * Created by nuwan.n.bandara on 5/25/2014.
 */
public interface UserDao {

   public User getUser(String userName) throws HelixDaoException;

   public void updateUser(User user) throws HelixDaoException;

}
