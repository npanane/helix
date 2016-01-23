package com.framework.helix.dao;

import com.framework.helix.dao.impl.ClientDaoImpl;
import com.framework.helix.entity.Client;
import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixDaoException;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

/**
 * @author nuwan
 * @since July 5, 2015
 * @version 1.0

 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/application-service.xml")
//@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class ClientDaoTest{
    /*@Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;*/

    @Autowired
    private ClientDao clientDao;

    @Test
    public void testSomething() {
        /*Session session = SessionFactoryUtils.getSession(sessionFactory, false);
        session.get(User.class, "me@here.com");*/
    }

    @Test
    public void getClient() throws HelixDaoException {
        Client client = clientDao.getClient(2213);
        assertEquals("Chico Police Officers' Association", client.getClientName());
    }
}
