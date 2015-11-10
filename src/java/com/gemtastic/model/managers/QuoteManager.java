/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.managers;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.CommentWrapper;
import com.gemtastic.model.entities.resourcewrappers.ProductWrapper;
import com.gemtastic.model.entities.resourcewrappers.QuoteWrapper;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class QuoteManager {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    CommentManager cm;
    
    @EJB
    ProductManager pm;
    
    /**
     * Returns list of all existing quotes.
     * 
     * @return
     */
    public List<QuoteWrapper> getAllQuotes(){
        List<Quote> quotes = em.createNamedQuery("Query.findAll", Quote.class).getResultList();
        List<QuoteWrapper> wrappedQuotes = wrapQuotes(quotes);
        return wrappedQuotes;
    }
    
    /**
     * Finds one quote with the provided id.
     * 
     * @param id
     * @return QuoteWrapper
     */
    public QuoteWrapper getQuoteById(int id){
//        List<Quote> quotes = new ArrayList<>();
//        Quote quote = em.find(Quote.class, id);
        Quote q = new Quote();
        q.setId(id);
        List<Quote> quotes = em.createNamedQuery("Quote.findById", Quote.class).setParameter("id", q.getId()).getResultList();
//        quotes.add(quote);
        List<QuoteWrapper> wrapped = wrapQuotes(quotes);
        
        if(!wrapped.isEmpty()){
            return wrapped.get(0);
        } else {
            return null;
        }
    }
    
    private List<QuoteWrapper> wrapQuotes(List<Quote> quotes){
        List<QuoteWrapper> wrapped = new ArrayList<>();
        for(Quote q: quotes){
            List<CommentWrapper> comments = cm.getAllCommentsOff(q);
            List<ProductWrapper> products = pm.getAllProductsOff(q);
            QuoteWrapper wrapper = new QuoteWrapper(q, comments, products, new Link(null, "self"));
            wrapped.add(wrapper);
        }
         return wrapped;
    }
}
