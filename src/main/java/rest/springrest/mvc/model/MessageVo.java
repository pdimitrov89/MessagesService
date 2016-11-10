/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.model;

/**
 * Value object to represent the message in the request and response payload.
 * @author pdimitrov
 */
public class MessageVo {
    private String payload;

    public MessageVo() {
    }

    public MessageVo(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
