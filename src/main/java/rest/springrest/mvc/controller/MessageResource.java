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
    
    static {
        System.out.println("REST controller Initialized");
    }
 
    protected void setDaoProvider(DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }
 
    @RequestMapping(value = "/messages.json", method = RequestMethod.GET)
    public ResponseEntity<List<MessageVo>> listAllUsers() {
            List<MessageVo> users = new ArrayList<>();
            users.add(new MessageVo("MM1"));
            users.add(new MessageVo("MM2"));
            
            return new ResponseEntity<List<MessageVo>>(users, HttpStatus.OK);
    }
        
    /**
     * Posts new Message to the database.
     * @param msgType
     * @param msgVo
     * @param ucBuilder
     * @return 
     */
    @RequestMapping(value = "/messages/{type}", 
            method = RequestMethod.POST 
            )
    public ResponseEntity<Void> postMessage(@PathVariable("type") String msgType, @RequestBody MessageVo msgVo) {
        
        Message msg = null;
        try {
            msg = MessageFactory.make(msgType, msgVo, UUIDProvider.instance);
            msg.validate();
        } catch(Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);
        }
        try {
            daoProvider.getMessageDao().save(msg);
        } catch (Exception ex) {
            ex.printStackTrace(); 
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
 
     
 
 
}
