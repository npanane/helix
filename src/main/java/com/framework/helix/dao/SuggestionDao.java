package com.framework.helix.dao;

import com.framework.helix.entity.Staffsuggestion;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/3/2014.
 */
public interface SuggestionDao {

    public void saveStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException;

    public void updateStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException;

    public void deleteStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixDaoException;

    public Staffsuggestion getStaffSuggestion(Integer idSuggestion) throws HelixDaoException;

    public List<Staffsuggestion> getStaffSuggestions() throws HelixDaoException;

    public String getLastStaffSuggestionId() throws HelixDaoException;
}
