package com.framework.helix.service;

import com.framework.helix.entity.Staffsuggestion;
import com.framework.helix.exception.HelixServiceException;


import java.util.List;

/**
 * Created by nuwan.n.bandara on 9/3/2014.
 */
public interface SuggestionService {

    public void saveStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException;

    public void updateStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException;

    public void deleteStaffSuggestion(Staffsuggestion staffsuggestion) throws HelixServiceException;

    public Staffsuggestion getStaffSuggestion(Integer idSuggestion) throws HelixServiceException;

    public List<Staffsuggestion> getStaffSuggestions() throws HelixServiceException;

    public String getLastStaffSuggestionId() throws HelixServiceException;

}
