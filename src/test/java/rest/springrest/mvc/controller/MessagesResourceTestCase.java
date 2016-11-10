/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.controller;

import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rest.springrest.mvc.model.Message;
import rest.springrest.mvc.model.Message.MessageType;
/**
 *
 * @author pdimitrov
 */
public class MessagesResourceTestCase {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockDaoProvider daoProvider = new MockDaoProvider();
    
    @Before
    public void setup() {
        MessageResource res = new MessageResource();
        daoProvider.getMessages().clear();// empty mock dao provider for every test
        res.setDaoProvider(daoProvider); 
        this.mockMvc = MockMvcBuilders.standaloneSetup(res).build();
    }
    
    
    @Test
    public void testSendEmotion() throws Exception {
        // valid payload 
        ResultActions res = this.mockMvc.perform(post("/messages/send_emotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\" :) \"}")
        );
        res.andExpect(status().isCreated())
            .andExpect(content().bytes(new byte[0]));
        // assert the message we expect
        assertEquals(1, daoProvider.getMessages().size());
        assertEquals(" :) ", daoProvider.getMessages().get(0).getMsg());
        assertEquals(MessageType.send_emotion, daoProvider.getMessages().get(0).getType());
        assertNotNull(daoProvider.getMessages().get(0).getCreatedAt());
        assertNotNull(daoProvider.getMessages().get(0).getId());
        
    //    Mockito.when(daoProvider.)
        // invalid payload - to short
        res = this.mockMvc.perform(post("/messages/send_emotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\".\"}")
        );
        res.andExpect(status().isPreconditionFailed())
            .andExpect(content().bytes(new byte[0]));
        
        // invalid payload - to long
        res = this.mockMvc.perform(post("/messages/send_emotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\".1234567891011\"}")
        );
        res.andExpect(status().isPreconditionFailed())
            .andExpect(content().bytes(new byte[0]));
        // assert there are no new messages
        assertEquals(1, daoProvider.getMessages().size());
    }

    @Test
    public void testSendText() throws Exception {
        // valid payload 
        ResultActions res = this.mockMvc.perform(post("/messages/send_text")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\"Hello\"}")
        );
        res.andExpect(status().isCreated())
            .andExpect(content().bytes(new byte[0]));
        
        // assert the message we expect
        assertEquals(1, daoProvider.getMessages().size());
        assertEquals("Hello", daoProvider.getMessages().get(0).getMsg());
        assertEquals(MessageType.send_text, daoProvider.getMessages().get(0).getType());
        assertNotNull(daoProvider.getMessages().get(0).getCreatedAt());
        assertNotNull(daoProvider.getMessages().get(0).getId());
        
        // invalid payload - to short
        res = this.mockMvc.perform(post("/messages/send_text")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\"\"}")
        );
        res.andExpect(status().isPreconditionFailed())
            .andExpect(content().bytes(new byte[0]));
        
        StringBuilder payloadBuilder = new StringBuilder(); 
        for(int i=0; i < 40; i++) {
            payloadBuilder.append("Hello"); // 40 * 5 = 200 characters.
        }
        // invalid payload - to long
        res = this.mockMvc.perform(post("/messages/send_text")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"payload\":\"" + payloadBuilder.toString() + "\"}")
        );
        res.andExpect(status().isPreconditionFailed())
            .andExpect(content().bytes(new byte[0]));
        
        // assert there are no new messages
        assertEquals(1, daoProvider.getMessages().size());
    }
}