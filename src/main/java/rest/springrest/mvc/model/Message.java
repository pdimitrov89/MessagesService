/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.HibernateException;
import org.hibernate.type.TimestampType;


/**
 * Persistent business object representing a message.
 * 
 * @author pdimitrov
 */
public class Message {

    public enum MessageType {
        send_text(1, 160), // min 1 and max 160 symbols
        send_emotion(2, 10) { // min 2 and max 10 symbols
            
            @Override
            public void validateMsg(String msg) {
                super.validateMsg(msg);
                Pattern pNumeric = Pattern.compile("[0-9]+"); // numeric
                Matcher mNumeric = pNumeric.matcher(msg);
                if (mNumeric.find()) {
                    throw new IllegalArgumentException("The message of type send_emotion can not contain numeric characters");
                }
            }
        }; 
        
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
    
    // add version if optimistic lock is requared, for this example skipp it. 
//  private long version; 

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
    private Timestamp getCreatedAtAsDate() {
        return Timestamp.valueOf((LocalDateTime)createdAt);
    }
    private void setCreatedAtAsDate(Timestamp d) {
        this.createdAt = d == null? null : d.toLocalDateTime();
    }
    
    public void validate() {
        if (type == null) {
            throw new IllegalStateException("Missing type.");
        }
        type.validateMsg(msg);
    }
}
