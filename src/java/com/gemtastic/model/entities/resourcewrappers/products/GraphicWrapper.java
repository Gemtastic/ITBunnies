/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gemtastic.model.entities.resourcewrappers.products;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class GraphicWrapper implements ProductInterface<Graphic> {

    String type;
    Graphic product;
    Link link;

    
    public GraphicWrapper() {
        
    }

    public GraphicWrapper(Graphic graphic, Link link) {
        this.product = graphic;
        this.link = link;
        this.type = "Graphic";
    }

    @Override
    public Graphic getProduct() {
        return product;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    @Override
    public void setProduct(Graphic product) {
        this.product = product;
    }
}
