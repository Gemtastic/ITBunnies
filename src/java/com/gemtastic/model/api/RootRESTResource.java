package com.gemtastic.model.api;

import com.gemtastic.model.api.RESTResources.Products;
import com.gemtastic.model.api.RESTResources.Quotes;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author Gemtastic
 */
@Path("/api")
public class RootRESTResource {
    
    @Context
    ResourceContext rc;
    
    @Path("/quotes")
    public Quotes getQuotesResource(){
        return rc.getResource(Quotes.class);
    }
    
    @Path("/products")
    public Products getProductsResource(){
        return rc.getResource(Products.class);
    }
}
