package com.gemtastic.model.entities.resourcewrappers;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Comment;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class CommentWrapper {
    
    private Link link;
    private Comment comment;

    public CommentWrapper() {
    }

    public CommentWrapper(Link link, Comment comment) {
        this.link = link;
        this.comment = comment;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    
}
