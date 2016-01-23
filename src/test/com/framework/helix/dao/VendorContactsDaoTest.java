package com.framework.helix.dao;

import com.framework.helix.entity.Client;
import com.framework.helix.entity.Contact;
import com.framework.helix.entity.User;
import com.framework.helix.entity.VendorContact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.service.VendorContactService;
import com.framework.helix.service.VendorService;
import com.framework.helix.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author nuwan
 * @since July 5, 2015
 * @version 1.0

 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/application-service.xml")
@Transactional
public class VendorContactsDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VendorDao getVendor;

    @Autowired
    private VendorContactsDao vendorContactsDao;

    @Autowired
    private VendorContactService vendorContactService;

    @Autowired
    private ContactDao contactDao;

    @Test
    public void getClient() {
        try {
            User user = userDao.getUser("SiNEP");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date currentDate = DateUtil.getSimpleDateFormat().parse(dateFormat.format(date));

            Contact contact = new Contact();
            contact.setMain("1");
            contact.setHome("1");
            contact.setMobile("1");
            contact.setOther("1");

            contact.setUser(user);
            contact.setDateCreated(currentDate);
            VendorContact vendorContact = new VendorContact();
            vendorContact.setVendor(getVendor.getVendor(602));
            vendorContact.setFirstName("Nuwan 1");
            vendorContact.setTitle("test title");
            vendorContact.setUser(user);
            vendorContact.setDateCreated(currentDate);
            vendorContact.setContact(contact);

            List<VendorContact> vendorContactList = new ArrayList<VendorContact>();
            vendorContactList.add(vendorContact);
            contact.setVendorContacts(vendorContactList);

            contactDao.saveOrUpdateContact(contact);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
