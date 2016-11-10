/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.springrest.mvc.model.Message;
import rest.springrest.mvc.model.MessageFactory;
import rest.springrest.mvc.model.MessageVo;
import rest.springrest.mvc.persistance.util.UUIDProvider;
import rest.springrest.mvc.persistance.util.DaoProvider;
/**
 * The controller for messages.
 * 
 * @author pdimitrov
 */
@RestController
public class MessageResource {
    
    /**
    * DaoProvider injected by Spring.
    */
    @Autowired
    private DaoProvider daoProvider;
    
    /**
     * Setter for daoProvider this method is used only for test setup purposes.
     * @param daoProvider 
     */
    protected void setDaoProvider(DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }
 
    /**
     * Posts new Message to the database.
     * @param msgType - could be one of send_text or send_emotion.
     * @param msgVo - the body of the request of format {"payload":"some characters"}
     * @return ResponseEntity with status 201 Created if the preconditions are met, 
     * or status 412 PRECONDITION_FAILED otherwise. I can also return status 500 INTERNAL_SERVER_ERROR.
     */
    @RequestMapping(value = "/messages/{type}", method = RequestMethod.POST)
    public ResponseEntity<Void> postMessage(@PathVariable("type") String msgType, @RequestBody MessageVo msgVo) {
        
        Message msg = null;
        try {
            msg = MessageFactory.make(msgType, msgVo, UUIDProvider.instance);
            msg.validate();
        } catch(Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);
        }
        // we have valid message, so log into DB. 
        try {
            daoProvider.getMessageDao().save(msg);
        } catch (Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
}
