/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.springrest.mvc.persistance;

import rest.springrest.mvc.persistance.util.DaoProvider;
import rest.springrest.mvc.persistance.util.PrepareTestPersistance;

/**
 * Base class for persistence test cases. Knows how to prepare the environment.
 * 
 * @author pdimitrov
 */
public class AbstractPersistanceTestCase {
    
    private static PrepareTestPersistance persistance;
    static {
        persistance = PrepareTestPersistance.getInstance();
        // add shutdown hook to free resources used by PrepareTestPersistance.
        Runtime.getRuntime().addShutdownHook(new Thread( 
            () -> {
                PrepareTestPersistance.closeInstance();
            }
        ));
    }
    
    public DaoProvider getDaoProvider() {
        return persistance.getDaoProvider();
    }
    
}
