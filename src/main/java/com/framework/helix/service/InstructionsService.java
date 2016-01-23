package com.framework.helix.service;

import com.framework.helix.entity.Clientselectioninstruction;
import com.framework.helix.exception.HelixServiceException;
import org.dom4j.Document;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public interface InstructionsService {

    public void saveInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixServiceException;

    public void saveOrUpdateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixServiceException;

    public List<Clientselectioninstruction> getInstructions(Integer idClient) throws HelixServiceException;

    public void deleteInstruction(int idInstruction) throws HelixServiceException;

    public Clientselectioninstruction getInstruction(int idInstruction) throws HelixServiceException;

    public void updateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixServiceException;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Document buildInstructionsResponseXML(Integer rowId) throws HelixServiceException;
}



