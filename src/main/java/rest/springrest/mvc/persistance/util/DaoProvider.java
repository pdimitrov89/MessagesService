/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.DisposableBean;
import rest.springrest.mvc.persistance.MessageDao;
import rest.springrest.mvc.persistance.MessageDaoImpl;

/**
 * Request scoped bean to provide Hibernate Session for the duration of the request.
 * 
 * @author pdimitrov
 */
public class DaoProvider implements DisposableBean {
    
    private SessionFactory sessionFactory;
    private Session hbmSession;
    
    private MessageDao msgDao;

    public DaoProvider() {
        // used by spring CGLIB
    }
    
    public DaoProvider(HibernateUtil hbUtil) {
        this(hbUtil.getSessionFactory());
    }
    
    public DaoProvider(SessionFactory sFactory) {
        this.sessionFactory = sFactory;
    }
    
    public Session getSession() {
        if (hbmSession == null) {
            hbmSession = sessionFactory.openSession();
        }
        return hbmSession;
    }
    
    public MessageDao getMessageDao() {
        if (msgDao == null) {
            msgDao = new MessageDaoImpl(getSession());
        }
        return msgDao;
    }

    @Override
    public void destroy() throws Exception {
        if (hbmSession != null && hbmSession.isOpen()) {
            hbmSession.close();
        }
    }
}
