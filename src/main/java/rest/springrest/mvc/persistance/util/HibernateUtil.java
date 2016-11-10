/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Knows how to obtain Hibernate SessionFactory.
 * Exposed as singleton bean.
 * 
 * @author pdimitrov
 */
public class HibernateUtil {

    private static final String HBM_CONFIG_FILE = "/hibernate/hibernate.cfg.xml";
    
    private Configuration cfg;
    private final SessionFactory sessionFactory;

    public HibernateUtil() {
        cfg = new Configuration();
        sessionFactory = buildSessionFactory();
    }

    
    private SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            cfg.configure(HBM_CONFIG_FILE);
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
