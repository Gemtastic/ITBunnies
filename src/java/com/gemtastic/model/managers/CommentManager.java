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

/**
 *
 * @author Gemtastic
 */
@Stateless
public class CommentManager {

    @PersistenceContext
    private EntityManager em;
    
    public List<CommentWrapper> getAllCommentsOff(Quote q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<CommentWrapper> wrapComments(List<Comment> comments){
        List<CommentWrapper> wrapped = new ArrayList<>();
        for(Comment c : comments) {
            wrapped.add(new CommentWrapper(null, c));
        }
        
        return wrapped;
    }
}
