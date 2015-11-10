package com.gemtastic.model.entities.resourcewrappers.products;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Website;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gemtastic
 */
@XmlRootElement
public class WebsiteWrapper implements ProductInterface<Website> {
    
    String type;
    Website product;
    Link link;

    public WebsiteWrapper() {
    }

    public WebsiteWrapper(Website website, Link link) {
        this.product = website;
        this.link = link;
        this.type = "Website";
    }

    @Override
    public Website getProduct() {
        return product;
    }

    @Override
    public void setProduct(Website product) {
        this.product = product;
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
    
}
