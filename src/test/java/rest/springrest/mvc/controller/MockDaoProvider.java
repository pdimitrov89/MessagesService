/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import rest.springrest.mvc.model.Message;
import rest.springrest.mvc.persistance.MessageDao;
import rest.springrest.mvc.persistance.util.DaoProvider;

/**
 * Mock DaoProvider which just stores saved entities.
 * 
 * @author pdimitrov
 */
public class MockDaoProvider extends DaoProvider {
    
    private static class MockDao implements MessageDao {
        List<Message> msgs = new ArrayList<>();
        
        @Override
        public void save(Message msg) {
            msgs.add(msg);
        }

        @Override
        public List<Message> list() {
            return msgs;
        }
    }
    
    private MockDao msgDao = new MockDao();
    
    @Override
    public MessageDao getMessageDao() {
        return msgDao;
    }
    
    public List<Message> getMessages() {
        return msgDao.msgs;
    }
}
