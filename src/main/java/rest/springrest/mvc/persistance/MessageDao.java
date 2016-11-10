/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance;

import java.util.List;
import rest.springrest.mvc.model.Message;

/**
 * Dao for Message instances.
 * 
 * @author pdimitrov
 */
public interface MessageDao {
    public void save(Message msg);
    
    public List<Message> list();
}
