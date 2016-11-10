/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance;

import java.util.List;
import org.hibernate.Session;
import rest.springrest.mvc.model.Message;

/**
 * Dao implementation for Message instances.
 * 
 * @author pdimirov
 */
public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {
    
    public MessageDaoImpl(Session s) {
        super(s);
    }
    
    public void save(Message msg) {
        super.save(msg);
    }
    
    public List<Message> list() {
        return super.list(Message.class.getName());
    }
    
}
