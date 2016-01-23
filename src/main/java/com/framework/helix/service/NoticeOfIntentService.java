package com.framework.helix.service;

import com.framework.helix.entity.Noticeofintent;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface NoticeOfIntentService {

    public void deleteNoticeDetails(int idIntent) throws HelixServiceException;

    public void saveNoticeDetails(Noticeofintent noticeofintent) throws HelixServiceException;

    public void updateNoticeDetails(Noticeofintent noticeofintent) throws HelixServiceException;

    public void saveOrUpdateNoticeDetails(Noticeofintent noticeofintent)  throws HelixServiceException;

    public void deleteNoticeDetails(Noticeofintent noticeofintent) throws HelixServiceException;

    public Noticeofintent getNoticeDetails(Integer idIntent) throws HelixServiceException;

    public List<Noticeofintent> getNoticeDetails() throws HelixServiceException;

    public  List<Noticeofintent> getNoticeDetailsCI(Integer idClient) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildNoticeOfIntentDetailsResponseXML(Integer ClientId) throws HelixServiceException;
}
