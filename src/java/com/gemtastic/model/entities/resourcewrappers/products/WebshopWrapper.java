package com.gemtastic.model.entities.resourcewrappers.products;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Webshop;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class WebshopWrapper implements ProductInterface<Webshop> {
    
    String type;
    Webshop product;
    Link link;

    public WebshopWrapper() {
    }

    public WebshopWrapper(Webshop webshop, Link link) {
        this.type = "Webshop";
        this.product = webshop;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    @Override
    public Webshop getProduct() {
        return product;
    }

    @Override
    public void setProduct(Webshop webshop) {
        this.product = webshop;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
}
