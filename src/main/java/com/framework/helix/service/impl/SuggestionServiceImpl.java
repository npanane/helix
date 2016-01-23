package com.framework.helix.service.impl;

import com.framework.helix.dao.SuggestionDao;
import com.framework.helix.entity.Staffsuggestion;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/3/2014.
 */
public class SuggestionServiceImpl implements SuggestionService {

    private SuggestionDao suggestionDao;

    @Autowired
    public void setSuggestionDao(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }


    public void saveStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException {
        try {
            suggestionDao.saveStaffSuggestion(staffsuggestion);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save staff suggestion.", e);
        }
    }

    public void updateStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException {
        try {
            suggestionDao.updateStaffSuggestion(staffsuggestion);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update staff suggestion.", e);
        }
    }

    public void deleteStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException {
        try {
            suggestionDao.deleteStaffSuggestion(staffsuggestion);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to staff suggestion address.", e);
        }
    }

    public Staffsuggestion getStaffSuggestion(Integer idSuggestion)  throws HelixServiceException {
        try {
            return suggestionDao.getStaffSuggestion(idSuggestion);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get Staff suggestion.", e);
        }
    }

    public List<Staffsuggestion> getStaffSuggestions() throws HelixServiceException {
        try {
            return suggestionDao.getStaffSuggestions();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get Staff suggestion.", e);
        }
    }

    public String getLastStaffSuggestionId() throws HelixServiceException {
        try {
            return suggestionDao.getLastStaffSuggestionId();
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get Staff suggestion Id.", e);
        }
    }


}
