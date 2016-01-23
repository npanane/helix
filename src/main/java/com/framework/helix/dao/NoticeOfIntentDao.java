package com.framework.helix.dao;

import com.framework.helix.entity.Noticeofintent;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/9/2014.
 */
public interface NoticeOfIntentDao {

    public void deleteNoticeDetails(int idIntent) throws HelixDaoException;

    public void saveNoticeDetails(Noticeofintent noticeofintent) throws HelixDaoException;

    public void updateNoticeDetails(Noticeofintent noticeofintent) throws HelixDaoException;

    public void saveOrUpdateNoticeDetails(Noticeofintent noticeofintent) throws HelixDaoException;

    public void deleteNoticeDetails(Noticeofintent noticeofintent) throws HelixDaoException;

    public Noticeofintent getNoticeDetails(Integer idIntent) throws HelixDaoException;

    public List<Noticeofintent> getNoticeDetails() throws HelixDaoException;

    public List<Noticeofintent> getNoticeDetailsCI(Integer idClient) throws HelixDaoException;
}
