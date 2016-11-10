/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance;

import java.time.LocalDateTime;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import rest.springrest.mvc.model.Message;
import rest.springrest.mvc.model.Message.MessageType;

/**
 * Test case for MessageDao.
 * 
 * @author pdimitrov
 */
public class MessageDaoTestCase extends AbstractPersistanceTestCase {
    
    @Test
    public void testSave() {
        MessageDao messageDAO = getDaoProvider().getMessageDao();

        Message msg = new Message("myId");
        msg.setMsg("Hello"); 
        msg.setType(Message.MessageType.send_text);
        LocalDateTime createdAt = LocalDateTime.now();
        msg.setCreatedAt(createdAt);

        messageDAO.save(msg);

        List<Message> list = messageDAO.list();
        assertEquals(1, list.size());
        Message m = list.get(0);
        
        assertEquals("myId", m.getId());
        assertEquals("Hello", m.getMsg());
        assertEquals(MessageType.send_text, m.getType());
        assertEquals(createdAt.toString(), m.getCreatedAt().toString());
    }
    
}
