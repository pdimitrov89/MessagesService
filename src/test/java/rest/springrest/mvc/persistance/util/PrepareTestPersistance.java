/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Helper class responsible for initializing of the in-memory db.
 * @author pdimitrov
 */
public class PrepareTestPersistance {
    
    private static PrepareTestPersistance instance;
    
    private DaoProvider daoProvider;
    private ApplicationContext springCtx;
    
    private PrepareTestPersistance() {
        springCtx = new ClassPathXmlApplicationContext("spring-test.xml");
        daoProvider = springCtx.getBean(DaoProvider.class);
    }

    public DaoProvider getDaoProvider() {
        return daoProvider;
    }

    public static PrepareTestPersistance getInstance() {
        if (instance == null) {
            instance = new PrepareTestPersistance();
        }
        return instance;
    }
    
    /**
     * Free resources used.
     */
    public static void closeInstance() {
        if (instance != null) {
            try {
                // close the active session
                instance.daoProvider.destroy();
            } catch (Exception ex) {
                Logger.getLogger(PrepareTestPersistance.class.getName()).log(Level.SEVERE, null, ex);
            }
            // close spring context
            ((ClassPathXmlApplicationContext)instance.springCtx).close();
            
            instance = null;
        }
    }
    
}
