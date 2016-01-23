package com.framework.helix.dao;

import com.framework.helix.entity.Clientselectioninstruction;
import com.framework.helix.exception.HelixDaoException;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface InstructionsDao {

    public void saveInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException;

    public void saveOrUpdateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException;

    public List<Clientselectioninstruction> getInstructions(Integer idClient) throws HelixDaoException;

    public void deleteInstruction(int idInstruction) throws HelixDaoException;

    public Clientselectioninstruction getInstruction(int idInstruction) throws HelixDaoException;

    public void updateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixDaoException;


}
