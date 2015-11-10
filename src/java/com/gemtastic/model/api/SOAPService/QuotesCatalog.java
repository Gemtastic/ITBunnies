/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.api.SOAPService;

import com.gemtastic.model.entities.database.Comment;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.CommentWrapper;
import com.gemtastic.model.entities.resourcewrappers.QuoteWrapper;
import com.gemtastic.model.managers.CommentManager;
import com.gemtastic.model.managers.QuoteManager;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Gemtastic
 */
@WebService
public class QuotesCatalog {
    
    @EJB
    QuoteManager qm;
    
    @EJB
    CommentManager cm;
    
    @WebMethod
    public List<QuoteWrapper> getAllQuotes(){
        List<QuoteWrapper> quotes = qm.getAllQuotes();
        return quotes;
    }
    
    @WebMethod
    public QuoteWrapper getQuoteById(int id){
        QuoteWrapper quote = qm.getQuoteById(id);
        return quote;
    }
    
    @WebMethod
    public List<CommentWrapper> getAllCommentsToQuote(int quoteId){
        List<CommentWrapper> comments = qm.getQuoteById(quoteId).getComments();
        return comments;
    }
    
    @WebMethod
    public CommentWrapper makeCommentOnQuote(String message, String author, int quote){
        Comment c = createComment(message, author, quote);
        CommentWrapper comment = cm.createComment(c);
        
        return comment;
    }
    
    private Comment createComment(String message, String author, int quote){
        Comment c = new Comment();
        c.setAuthor(author);
        c.setCreated(new Date());
        c.setMessage(message);
        Quote q = qm.getQuoteById(quote).getQuote();
        c.setQuote(q);
        
        return c;
    }
}
