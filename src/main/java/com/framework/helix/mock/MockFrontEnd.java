package com.framework.helix.mock;

import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: nuwan.n.panane
 * Date: 2/10/14
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockFrontEnd {
    public static String getEvents() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\events.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getloadPendingDataEntries() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\pending_data_entry.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getClients() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\clients.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getAddresses() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\addresses.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getPhones() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\phones.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getClientContacts() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\client_contacts.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getContacts() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\contacts.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }


    public static String getDriveDetails() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\driveDetails.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getCampaignDetails() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\campaignDetails.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getNotes() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\notes.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static String getInstructions() throws HelixServiceException {
        String values = null;
        try {
            values = parse("D:\\apache-tomcat-7.0.47\\webapps\\helix-mock-xml\\instructions.xml").asXML();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static Document parse(String fileName) throws DocumentException {
        File xml = new File(fileName);
        SAXReader reader = new SAXReader();
        return reader.read(xml);
    }
}
