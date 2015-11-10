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
 * Wraps the different types of products into one list.
 * 
 * @author Gemtastic
 */
@XmlRootElement
public class ProductWrapper {
    
    private List<ProductInterface> products;
    Link link;

    public ProductWrapper() {
    }

    public ProductWrapper(List<ProductInterface> products, Link link) {
        this.products = products;
        this.link = link;
    }

    public List<ProductInterface> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInterface> products) {
        this.products = products;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
}
