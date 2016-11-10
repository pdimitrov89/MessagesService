/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


/**
 *
 * @author pdimitrov
 */
public class Message {

    public enum MessageType {
        send_text(1, 160), 
        send_emotion(2, 10);
        
        private int minSize;
        private int maxSize;
        
        MessageType(int minSize, int maxSize) {
            this.minSize = minSize;
            this.maxSize = maxSize;
        }
    
        public void validateMsg(String msg) {
            int len = msg == null ? 0: msg.length();
            if (len < minSize || len > maxSize) {
                throw new IllegalArgumentException("The message of type:" + name() + " must be between " + minSize +" and "+ maxSize+" symbols.");
            }
        }
    }
    
    private String id;
    private String msg;
    private MessageType type;
    private LocalDateTime createdAt;
    
    private long version; 

    public Message(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null.");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }
    
    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * Getter and setter used by hibernate.
     * @return 
     */
    private String getTypeAsString() {
        return type == null ? null : type.name();
    }
    private void setTypeAsString(String typeStr) {
        this.type = typeStr == null? null : MessageType.valueOf(typeStr);
    }
    
    /**
     * Getter and setter used by hibernate.
     * @return 
     */
    private Date getCreatedAtAsDate() {
        return createdAt == null ? null : Date.from(createdAt.toInstant(ZoneOffset.UTC));
    }
    private void setCreatedAtAsDate(Date d) {
        this.createdAt = d == null? null : LocalDateTime.ofInstant(d.toInstant(), ZoneOffset.UTC);
    }
    
    public void validate() {
        if (type == null) {
            throw new IllegalStateException("Missing type.");
        }
        type.validateMsg(msg);
    }
}
