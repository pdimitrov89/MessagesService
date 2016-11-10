/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

import rest.springrest.mvc.persistance.util.IdProvider;
import java.util.UUID;

/**
 *
 * @author pdimitrov
 */
public class UUIDProvider implements IdProvider<String> {

    public static final UUIDProvider instance = new UUIDProvider();
    
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
    
}
