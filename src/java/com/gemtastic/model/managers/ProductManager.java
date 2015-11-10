package com.gemtastic.model.managers;

import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.ProductWrapper;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class ProductManager {
    
    @PersistenceContext
    EntityManager em;

    public List<ProductWrapper> getAllProductsOff(Quote q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
