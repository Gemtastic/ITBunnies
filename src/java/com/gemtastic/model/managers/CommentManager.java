/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.managers;

import com.gemtastic.model.entities.database.Comment;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.CommentWrapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author Gemtastic
 */
@Stateless
public class CommentManager {

    @PersistenceContext
    private EntityManager em;
    
    public List<CommentWrapper> getAllCommentsOff(int quoteId) {
        List<CommentWrapper> wrapped = new ArrayList<>();
        try{
            Quote quote = em.find(Quote.class, quoteId);
            if(quote != null) {
                List<Comment> cw = quote.getCommentList();
                wrapped = wrapComments(cw);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        
        return wrapped;
    }

    public void deleteComment(int id) {
        try{
            Comment c = em.find(Comment.class, id);
            if(c != null) {
                em.remove(c);
            }
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    public CommentWrapper getComment(int id) {
        List<CommentWrapper> wrapped = null;
        try {
            Comment c = em.find(Comment.class, id);
            if(c != null){
                List<Comment> list = new ArrayList<>();
                list.add(c);
                wrapped = wrapComments(list);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        if(wrapped != null) {
            return wrapped.get(0);
        }
        return null;
    }

    public CommentWrapper createComment(Comment comment) {
        Comment c = null;
        try {
            c = em.merge(comment);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        
        if(c != null){
            List<Comment> list = new ArrayList<>();
            list.add(c);
            List<CommentWrapper> wrapped = wrapComments(list);
            return wrapped.get(0);
        }
        return null;
    }

    public CommentWrapper updateComment(Comment comment) {
        List<Comment> list = new ArrayList<>();
        try{
            Comment c = em.find(Comment.class, comment.getId());
            if (c != null) {
                list.add(c);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        List<CommentWrapper> wrapped = wrapComments(list);
        if(!wrapped.isEmpty()){
            return wrapped.get(0);
        } else {
            return null;
        }
    }
    
    private List<CommentWrapper> wrapComments(List<Comment> comments){
        List<CommentWrapper> wrapped = new ArrayList<>();
        
        for(Comment c : comments){
            wrapped.add(new CommentWrapper(null, c));
        }
        return wrapped;
    }
}
