package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientContactsDao;
import com.framework.helix.entity.Clientcontact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.ClientContactsService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public class ClientContactsServiceImpl implements ClientContactsService {

    private static final Logger logger = Logger.getLogger(ClientContactsServiceImpl.class);

    private ClientContactsDao clientContactsDao;

    @Autowired
    public void setClientContactsDao(ClientContactsDao clientContactsDao) {
        this.clientContactsDao = clientContactsDao;
    }

    public void saveClientContact(Clientcontact clientcontact) throws HelixServiceException {
        try {
            clientContactsDao.saveClientContact(clientcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save client contact.", e);
        }
    }

    public List<Clientcontact> getClientContacts(Integer idClient) throws HelixServiceException {
        try {
            return clientContactsDao.getClientContacts(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client contacts.", e);
        }
    }

    public void deleteClientContact(int idClientContact) throws HelixServiceException {
        try {
            clientContactsDao.deleteClientContact(idClientContact);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete client contacts.", e);
        }
    }

    public Clientcontact getClientContact(int idClientContact) throws HelixServiceException {
        try {
            return clientContactsDao.getClientContact(idClientContact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client contacts.", e);
        }
    }

    public void updateClientContact(Clientcontact clientcontact) throws HelixServiceException {
        try {
            clientContactsDao.updateClientContact(clientcontact);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get  client contacts.", e);
        }
    }

    public Clientcontact getClientContactByName(String ContactName) throws HelixServiceException {
        try {
            return clientContactsDao.getClientContactByName(ContactName);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get  client contacts.", e);
        }
    }

    public Document buildContactsResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("clientContacts");
        List<Clientcontact> clientContactsList = getClientContacts(rowId);
        List<Clientcontact> preparedList = prepareClientContact(clientContactsList);
        if (clientContactsList!=null) {
            for (Clientcontact clientContact : preparedList) {
                Element element = root.addElement("contact");
                element.addAttribute("clientContactId", String.valueOf(clientContact.getIdClientContact()));
                element.addAttribute("name", clientContact.getFirstName()+" "+clientContact.getLastName());
                element.addAttribute("title",clientContact.getTitle());
                String isPast = "Current";
                if (clientContact.getTitle() != null && clientContact.getTitle().contains("Past")) {
                    isPast = "Past";
                }
                element.addAttribute("isPast", isPast);
                element.addAttribute("phone", (clientContact.getContact() != null)
                        ? clientContact.getContact().getMain() : "");
                element.addAttribute("home", (clientContact.getContact() != null)
                        ? clientContact.getContact().getHome() : "");
                element.addAttribute("mobile", (clientContact.getContact() != null)
                        ? clientContact.getContact().getMobile() : "");
                element.addAttribute("other", (clientContact.getContact() != null)
                        ? clientContact.getContact().getOther() : "");
                element.addAttribute("fax", (clientContact.getContact() != null)
                        ? clientContact.getContact().getFax() : "");
                element.addAttribute("email", (clientContact.getContact() != null)
                        ? clientContact.getContact().getEmail() : "");
            }
        }
        return document;
    }

    public Document buildSelectedClientContactResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("clientContacts");
        Clientcontact clientContact = getClientContact(rowId);
        if (clientContact != null) {
            Element element = root.addElement("contact");
            try {
                element.addAttribute("rank", clientContact.getRank());
                element.addAttribute("firstName", clientContact.getFirstName());
                element.addAttribute("lastName", clientContact.getLastName());
                element.addAttribute("title", clientContact.getTitle());
                element.addAttribute("address", (clientContact.getAddress() != null)
                        ? clientContact.getAddress().getStreetName() : "");
                element.addAttribute("city", (clientContact.getAddress() != null) ?
                        clientContact.getAddress().getCity() : "");
                element.addAttribute("state", (clientContact.getAddress() != null
                        && clientContact.getAddress().getState() != null)
                        ? clientContact.getAddress().getState().getIdState().toString() : "0");
                element.addAttribute("zip", (clientContact.getAddress() != null) ?
                        clientContact.getAddress().getZipCode() : "");
                if (clientContact.getAddress() == null) {
                    logger.warn("Address is null. Related fields are null.");
                }
                element.addAttribute("main", (clientContact.getContact() != null) ?
                        clientContact.getContact().getMain() : "");
                element.addAttribute("home", (clientContact.getContact() != null) ?
                        clientContact.getContact().getHome() : "");
                element.addAttribute("mobile", (clientContact.getContact() != null) ?
                        clientContact.getContact().getMobile() : "");
                element.addAttribute("other", (clientContact.getContact() != null) ?
                        clientContact.getContact().getOther() : "");
                element.addAttribute("fax", (clientContact.getContact() != null) ?
                        clientContact.getContact().getFax() : "");
                element.addAttribute("email", (clientContact.getContact() != null) ?
                        clientContact.getContact().getEmail() : "");
                element.addAttribute("alertEmail", (clientContact.getContact() != null) ?
                        clientContact.getContact().getEmailAlt() : "");
                element.addAttribute("note", clientContact.getNotes());
                element.addAttribute("EmailCheck", clientContact.getFlMonthlyStatementByEmail().toString());
                element.addAttribute("FaxCheck", clientContact.getFlMonthlyStatementByFax().toString());
                element.addAttribute("MailCheck", clientContact.getFlMonthlyStatementByMail().toString());
            }
            catch (NullPointerException e) {
                throw new HelixServiceException(e);
            }
        }
        return document;
    }

    private static List<Clientcontact> prepareClientContact(List<Clientcontact> clientContacts) {
        List<Clientcontact> past = new LinkedList<Clientcontact>();
        List<Clientcontact> curr = new LinkedList<Clientcontact>();
        for (Clientcontact clientContact : clientContacts) {
            if (clientContact.getTitle() != null && clientContact.getTitle().contains("Past")) {
                past.add(clientContact);
            }
            else {
                curr.add(clientContact);
            }
        }
        curr.addAll(past);
        return curr;
    }
}
