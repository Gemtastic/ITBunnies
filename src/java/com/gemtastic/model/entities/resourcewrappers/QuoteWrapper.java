package com.gemtastic.model.entities.resourcewrappers;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class QuoteWrapper {
    
    Quote quote;
    
    List<CommentWrapper> comments;
    
    List<ProductWrapper> products;
    
    Link link;

    public QuoteWrapper() {
    }

    public QuoteWrapper(Quote quote, List<CommentWrapper> comments, List<ProductWrapper> products, Link link) {
        this.quote = quote;
        this.comments = comments;
        this.products = products;
        this.link = link;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public List<CommentWrapper> getComments() {
        return comments;
    }

    public void setComments(List<CommentWrapper> comments) {
        this.comments = comments;
    }

    public List<ProductWrapper> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWrapper> products) {
        this.products = products;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
}
