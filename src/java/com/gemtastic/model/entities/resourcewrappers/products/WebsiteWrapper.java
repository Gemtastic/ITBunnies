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
public class WebsiteWrapper implements ProductInterface {
    
    String type;
    Website website;
    Link link;

    public WebsiteWrapper() {
    }

    public WebsiteWrapper(Website website, Link link) {
        this.website = website;
        this.link = link;
        this.type = "Website";
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
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
