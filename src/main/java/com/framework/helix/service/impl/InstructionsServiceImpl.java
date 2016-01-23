package com.framework.helix.service.impl;

import com.framework.helix.dao.InstructionsDao;
import com.framework.helix.entity.Clientselectioninstruction;
import com.framework.helix.exception.HelixDaoException;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.InstructionsService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nuwan.n.bandara on 7/10/2014.
 */
public class InstructionsServiceImpl implements InstructionsService {

    private InstructionsDao instructionsDao;

    @Autowired
    public void setInstructionsDao(InstructionsDao instructionsDao) {
        this.instructionsDao = instructionsDao;
    }

    public void saveInstruction(Clientselectioninstruction clientselectioninstruction)  throws HelixServiceException {
        try {
            instructionsDao.saveInstruction(clientselectioninstruction);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save instruction.", e);
        }
    }

    public void saveOrUpdateInstruction(Clientselectioninstruction clientselectioninstruction)  throws HelixServiceException {
        try {
            instructionsDao.saveOrUpdateInstruction(clientselectioninstruction);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to save instruction.", e);
        }
    }

    public List<Clientselectioninstruction> getInstructions(Integer idClient) throws HelixServiceException {
        try {
            return instructionsDao.getInstructions(idClient);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get instructions.", e);
        }
    }

    public void deleteInstruction(int idInstruction) throws HelixServiceException {
        try {
            instructionsDao.deleteInstruction(idInstruction);
        }
        catch (Exception e) {
            throw new HelixServiceException("Unable to delete instruction details.", e);
        }
    }

    public void updateInstruction(Clientselectioninstruction clientselectioninstruction) throws HelixServiceException {
        try {
            instructionsDao.updateInstruction(clientselectioninstruction);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to update instruction.", e);
        }
    }

    public Clientselectioninstruction getInstruction(int idInstruction) throws HelixServiceException {
        try {
            return instructionsDao.getInstruction(idInstruction);
        }
        catch (HelixDaoException e) {
            throw new HelixServiceException("Unable to get instruction.", e);
        }
    }

    public Document buildInstructionsResponseXML(Integer rowId) throws HelixServiceException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("instructions");
        List<Clientselectioninstruction> clientselectioninstructionList = getInstructions(rowId);
        if (clientselectioninstructionList !=null) {
            for (Clientselectioninstruction clientInstruction : clientselectioninstructionList) {
                Element element = root.addElement("instruction");
                element.addAttribute("instructionId", String.valueOf(clientInstruction.getIdInstruction()));
                element.addAttribute("instruction", clientInstruction.getInstruction());
            }
        }
        return document;
    }

}
