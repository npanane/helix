package com.framework.helix.service.impl;

import com.framework.helix.dao.NoticeOfIntentDao;
import com.framework.helix.entity.Noticeofintent;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.NoticeOfIntentService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public class NoticeOfIntentServiceImpl implements NoticeOfIntentService{

    private NoticeOfIntentDao noticeOfIntentDao;

    @Autowired
    public void setNoticeOfIntentDao(NoticeOfIntentDao noticeOfIntentDao) {
        this.noticeOfIntentDao = noticeOfIntentDao;
    }

    public void  deleteNoticeDetails(int idIntent) throws HelixServiceException {
        try {
            noticeOfIntentDao.deleteNoticeDetails(idIntent);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete notice of intent details.", e);
        }
    }

    public void saveNoticeDetails(Noticeofintent noticeofintent) throws HelixServiceException {
        try {
            noticeOfIntentDao.saveNoticeDetails(noticeofintent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save notice of intent details.", e);
        }
    }

    public void updateNoticeDetails(Noticeofintent noticeofintent)  throws HelixServiceException {
        try {
            noticeOfIntentDao.updateNoticeDetails(noticeofintent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update notice of intent details.", e);
        }
    }

    public void saveOrUpdateNoticeDetails(Noticeofintent noticeofintent)  throws HelixServiceException {
        try {
            noticeOfIntentDao.saveOrUpdateNoticeDetails(noticeofintent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save or update notice of intent details.", e);
        }
    }

    public void deleteNoticeDetails(Noticeofintent noticeofintent) throws HelixServiceException {
        try {
            noticeOfIntentDao.deleteNoticeDetails(noticeofintent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to delete notice of intent details.", e);
        }
    }

    public Noticeofintent getNoticeDetails(Integer idIntent) throws HelixServiceException {
        try {
            return noticeOfIntentDao.getNoticeDetails(idIntent);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get notice of intent details.", e);
        }
    }

    public List<Noticeofintent> getNoticeDetails() throws HelixServiceException {
        try {
            return noticeOfIntentDao.getNoticeDetails();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get notice of intent details.", e);
        }
    }


    public List<Noticeofintent> getNoticeDetailsCI(Integer idClient) throws HelixServiceException {
        try {
            return noticeOfIntentDao.getNoticeDetailsCI(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get notice of intent details.", e);
        }
    }

    public Document buildNoticeOfIntentDetailsResponseXML(Integer ClientId) throws HelixServiceException {
        List<Noticeofintent> noticeofintentList= getNoticeDetailsCI(ClientId);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("noticeOfIntent");
        if (noticeofintentList!=null) {
            for (Noticeofintent noticeofintent : noticeofintentList) {
                Element element = root.addElement("details");
                element.addAttribute("IdNoticeOfIntent", String.valueOf(noticeofintent.getIdIntent()));
                element.addAttribute("regNum",noticeofintent.getRegistrationNo());
                element.addAttribute("email",noticeofintent.getEmail());
                element.addAttribute("activity",noticeofintent.getActivity());
                if (noticeofintent.getBeginningDate() != null) {
                    element.addAttribute("beginningDate", noticeofintent.getBeginningDate().toString().substring(0, 10));
                }
                if (noticeofintent.getFlTelemarketing() != null) {
                    element.addAttribute("NOItelemarketing", noticeofintent.getFlTelemarketing().toString());
                }
                if (noticeofintent.getEndingDate() != null) {
                    element.addAttribute("endingDate", noticeofintent.getEndingDate().toString().substring(0, 10));
                }
            }
        }
        return document;
    }

}
