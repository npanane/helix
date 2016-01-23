package com.framework.helix.service.impl;

import com.framework.helix.dao.ClientDao;
import com.framework.helix.entity.Address;
import com.framework.helix.entity.Client;
import com.framework.helix.entity.ClientType;
import com.framework.helix.entity.Contact;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.AddressService;
import com.framework.helix.service.ClientService;
import com.framework.helix.service.ContactService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private ClientDao clientDao;
    private ContactService contactService;
    private AddressService addressService;

    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public void saveClient(Client client) throws HelixServiceException {
        try {
            clientDao.saveClient(client);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save client.", e);
        }
    }

    public void updateClient(Client client) throws HelixServiceException {
        try {
            clientDao.updateClient(client);
        }
        catch (HelixDaoException e) {
             throw new HelixServiceException("Unable to update client.", e);
        }
    }

    public void saveOrUpdateClient(Client client) throws HelixServiceException {
        try {
            clientDao.saveOrUpdateClient(client);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update client.", e);
        }
    }

    public void deleteClient(Client client) throws HelixServiceException {
        try {
            clientDao.deleteClient(client);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete client.", e);
        }
    }

    public Client getClient(Integer idClient) throws HelixServiceException {
        try {
            return clientDao.getClient(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get client.", e);
        }
    }

    public List<Client> getClients() throws HelixServiceException {
        try {
            return clientDao.getClients();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get clients.", e);
        }
    }

    public List<Client> getClients(String status) throws HelixServiceException {
        try {
            return clientDao.getClients(status);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get clients for clinet status " + status, e);
        }
    }

    public String getLastClientsId() throws HelixServiceException {
        try {
            return clientDao.getLastClientsId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get clientId.", e);
        }
    }

    public List<Client> getClientsForPoBoxPickup(String season, Integer year) throws HelixServiceException {
        try {
            return clientDao.getClientsForPoBoxPickup(season, year);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException(e);
        }
    }

    @Override
    public List<ClientType> getClientType() throws HelixServiceException {
        try {
            return clientDao.getClientType();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException(e);
        }
    }

    public Document buildClientResponseXML() throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("clients");
        List<Client> clients = null;
        clients = getClients("Client");
        for(Client client : clients) {
            Element element = root.addElement("client");
            element.addAttribute("clientId", String.valueOf(client.getIdClient()));
            element.addAttribute("name", client.getClientName());
            element.addAttribute("acronym", client.getCoClient());
            element.addAttribute("jurisdiction", client.getJurisdiction());
            element.addAttribute("idClientType", ( (client.getClientType() != null)
                    ? String.valueOf(client.getClientType().getIdClientType()) : "0"));
            element.addAttribute("clientType", ( (client.getClientType() != null)
                    ? client.getClientType().getClientType() : "0"));
        }
        return document;
    }

    public Document buildClientContactsResponseXML(Integer ClientId) throws HelixServiceException {
        Client client = getClient(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contact");
        Element element = root.addElement("details");
        if (client.getContact()!=null) {
            Contact contact = contactService.getContact(client.getContact().getIdContact());
            element.addAttribute("IdContact", String.valueOf(contact.getIdContact()));
            element.addAttribute("main",contact.getMain());
            element.addAttribute("dispatch",contact.getDispatch());
            element.addAttribute("fax", contact.getFax());
        }
        return document;
    }

    public Document buildClientAddressResponseXML(Integer ClientId) throws HelixServiceException {
        Client client = getClient(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("address");
        Element element = root.addElement("details");
        if (client.getAddress()!=null) {
            Address address = addressService.getAddress(client.getAddress().getIdAddress());
            element.addAttribute("IdAddress", String.valueOf(address.getIdAddress()));
            element.addAttribute("city",address.getCity());
            element.addAttribute("zip",address.getZipCode());
            element.addAttribute("state", ( (address.getState() != null)
                    ? String.valueOf(address.getState().getIdState()) : "0"));
            element.addAttribute("address1",address.getStreetName());
        }
        return document;
    }

    public Document buildMailContractDetailsResponseXML(Integer ClientId) throws HelixServiceException {
        Client client= getClient(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("MailContract");
        if (client != null) {
            Element element = root.addElement("details");
            element.addAttribute("commencementDate", (client.getCommencementDate() != null)
                    ? df.format(client.getCommencementDate()) : null);
            element.addAttribute("terminationDate", (client.getTerminationDate() != null)
                    ? df.format(client.getTerminationDate()) : null);
            if (client.getTerminationBuffer() != null) {
                element.addAttribute("terminationBuff", String.valueOf(client.getTerminationBuffer()));
            }
            if (client.getFlCharity() != null) {
                element.addAttribute("charity", String.valueOf(client.getFlCharity()));
            }
            if (client.getRenewperiod() != null) {
                element.addAttribute("renewPeriod", String.valueOf(client.getRenewperiod().getIdRenewPeriod()));
            }
            if (client.getFlTelemarketing() != null) {
                element.addAttribute("telemarketing", String.valueOf(client.getFlTelemarketing()));
            }
            if (client.getRenewtype() != null) {
                element.addAttribute("renewType", String.valueOf(client.getRenewtype().getIdRenewType()));
            }
            if (client.getFlHideContractAlert() != null) {
                element.addAttribute("hideContractAlerts", String.valueOf(client.getFlHideContractAlert()));
            }
            if (client.getFlCancellationLetter() != null) {
                element.addAttribute("cancelLetterReceived", String.valueOf(client.getFlCancellationLetter()));
            }
            element.addAttribute("cancellationDate", (client.getCancellationDate() != null)
                    ? df.format(client.getCancellationDate()) : null);
        }
        return document;
    }

    public Document buildNewsResponseXML(Integer clientId) throws HelixServiceException {
        URL url = null;
        Client client = getClient(clientId);
        Address address = (client.getAddress() != null)
                ? addressService.getAddress(client.getAddress().getIdAddress()) : null;
        Document document = DocumentHelper.createDocument();
        if (address != null) {
            Element root = document.addElement("newsList");
            try {
                String newsParam = (address != null && address.getCity() != null) ? address.getCity() + "+Police"
                        : client.getClientName();
                url = new URL("https://ajax.googleapis.com/ajax/services/search/news?" +
                        "v=1.0&q=" + newsParam + "&userip=INSERT-USER-IP");
                URLConnection connection = url.openConnection();
                connection.addRequestProperty("Referer", "http://98.189.119.139/sinep");
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                JSONObject json = new JSONObject(builder.toString());
                if (json.get("responseStatus").equals(200)) {
                    JSONObject responseData = (JSONObject) json.get("responseData");
                    JSONArray jsonArray = (JSONArray) responseData.get("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = jsonArray.getJSONObject(i);
                        /*
                        System.out.println("**********************************************************");
                        System.out.println("\t > Title: " + row.get("title"));
                        System.out.println("\t > Content: " + row.get("content"));
                        System.out.println("\t > Publiched Date: " + row.get("publishedDate"));
                        System.out.println("\t > Unescaped Url: " + row.get("unescapedUrl"));
                        System.out.println("**********************************************************");
                        */
                        Element element = root.addElement("news");
                        element.addAttribute("newsId", String.valueOf(1));
                        element.addAttribute("newsItem", "<span style='text-decoration: underline;'>"
                                + String.valueOf(row.get("title")) + "</span><br>" +
                                String.valueOf(row.get("content")));
                        element.addAttribute("url", String.valueOf(row.get("unescapedUrl")));
                    }
                }
            }
            catch (IOException e) {
                logger.error(e);
            }
            catch (Exception e) {
                logger.error(e);
            }
        }
        return document;
    }
}
