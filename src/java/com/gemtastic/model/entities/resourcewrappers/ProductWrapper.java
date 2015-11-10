/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.entities.resourcewrappers;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wraps the different types of product into one list.
 * 
 * @author Gemtastic
 */
@XmlRootElement
public class ProductWrapper {
    
    private ProductInterface product;
    Link link;

    public ProductWrapper() {
    }

    public ProductWrapper(ProductInterface products, Link link) {
        this.product = products;
        this.link = link;
    }

    public ProductInterface getProduct() {
        return product;
    }

    public void setProduct(ProductInterface products) {
        this.product = products;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
}
