/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.model;

import java.time.LocalDateTime;
import rest.springrest.mvc.persistance.util.IdProvider;
import rest.springrest.mvc.model.Message.MessageType;

/**
 * Factory for Message instances. 
 * 
 * @author pdimitrov
 */
public class MessageFactory {
    
    public static Message make(String type, MessageVo payload, IdProvider<String> idProvider) {
    
        MessageType msgType = MessageType.valueOf(type);
        
        if (payload == null) {
            throw new IllegalArgumentException("Missing payload!");
        }
        
        Message msg = new Message(idProvider.getId());
        msg.setType(msgType);
        msg.setMsg(payload.getPayload());
        msg.setCreatedAt(LocalDateTime.now());
        return msg;
    }
}
