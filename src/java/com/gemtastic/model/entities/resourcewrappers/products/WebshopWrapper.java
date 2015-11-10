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
public class WebshopWrapper implements ProductInterface {
    
    String type;
    Webshop webshop;
    Link link;

    public WebshopWrapper() {
    }

    public WebshopWrapper(Webshop webshop, Link link) {
        this.type = "Webshop";
        this.webshop = webshop;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public Webshop getWebshop() {
        return webshop;
    }

    public void setWebshop(Webshop webshop) {
        this.webshop = webshop;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
}
