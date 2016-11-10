/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rest.springrest.mvc.model.Message;

/**
 * Abstract Dao class to serve as parent for entity Dao's.  
 * 
 * @author pdimitrov
 */
public abstract class AbstractDao<Entity> {

    private Session session;
    
    public AbstractDao(Session s) {
        this.session = s;
    }
    
    public void save(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null.");
        }
        
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(entity);
            tr.commit();
            System.out.println("Saved");
        } catch (Exception ex) {
            if (tr != null) {
                tr.rollback();
            }
            throw ex;
        }
    }
    
    protected List<Entity> list(String entityName) { 
        List<Entity> entityList = session.createQuery("from " + entityName).list();
        session.close();
        return entityList;
    }
    
}
