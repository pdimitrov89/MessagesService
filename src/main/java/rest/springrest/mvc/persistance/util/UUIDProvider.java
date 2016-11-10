/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

import java.util.UUID;

/**
 * Implementation of the IdPrivider interface which knows how to generate UUID.
 * @author pdimitrov
 */
public class UUIDProvider implements IdProvider<String> {

    public static final UUIDProvider instance = new UUIDProvider();
    
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
