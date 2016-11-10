/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

/**
 *
 * @author pdimitrov
 */
public interface IdProvider<IdType> {
    public IdType getId();
}
