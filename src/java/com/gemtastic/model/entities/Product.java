/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class Product {
    
    private String type;
    private int id;
    private Link link;

    public Product() {
    }

    public Product(String type, int id, Link link) {
        this.type = type;
        this.id = id;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
}
