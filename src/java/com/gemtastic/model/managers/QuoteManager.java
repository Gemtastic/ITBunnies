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
import javax.management.Query;
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
        List<Quote> quotes = em.createNamedQuery("Quote.findAll", Quote.class).getResultList();
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

        List<Quote> quotes = em.createNamedQuery("Quote.findById", Quote.class).setParameter("id", id).getResultList();

        List<QuoteWrapper> wrapped = wrapQuotes(quotes);
        
        if(!wrapped.isEmpty()){
            return wrapped.get(0);
        } else {
            return null;
        }
    }
    
    public void deleteQuoteById(int id) {
        Quote quote = em.find(Quote.class, id);
        if(quote != null){
            em.remove(quote);
        }
    }
    
    public QuoteWrapper upsertQuote(Quote quote) {
        List<Quote> quotes = new ArrayList<>();
        
        Quote q = em.merge(quote);
        if(q != null){
            quotes.add(q);
            List<QuoteWrapper> wrapped = wrapQuotes(quotes);
            return wrapped.get(0);
        }
        return null;
    }
    
    private List<QuoteWrapper> wrapQuotes(List<Quote> quotes){
        List<QuoteWrapper> wrapped = new ArrayList<>();
        for(Quote q: quotes){
            List<CommentWrapper> comments = cm.getAllCommentsOff(q.getId());
            List<ProductWrapper> products = pm.getAllProductsOff(q);
            QuoteWrapper wrapper = new QuoteWrapper(q, comments, products, null);
            wrapped.add(wrapper);
        }
         return wrapped;
    }
}
