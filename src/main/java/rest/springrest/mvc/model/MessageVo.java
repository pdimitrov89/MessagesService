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
    
    @Override
    public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + (int) (id ^ (id >>> 32));
            return payload.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            MessageVo other = (MessageVo) obj;
            if (payload != other.payload)
                    return false;
            return true;
    }

    @Override
    public String toString() {
            return "MessageVo [payload=" + payload + "]";
    }
}
