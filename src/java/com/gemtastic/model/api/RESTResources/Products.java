package com.gemtastic.model.api.RESTResources;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.Product;
import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.managers.ProductManager;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * Product resource is read only to see what resources we offer.
 * 
 * @author Gemtastic
 */
@Path("/products")
public class Products {
    
    @EJB
    ProductManager pm;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(@Context UriInfo uriInfo, @Context Request request) {
        List<Product> products = null;
        try{
            products = pm.getAllProducts();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        if(products != null){
            int hashValue = 0;
            
            for(Product p : products){
                
                String uri = uriInfo.getBaseUriBuilder().path(p.getClass())
                                    .path(Integer.toString(p.getId()))
                                    .build()
                                    .toString();
                hashValue +=  p.hashCode();
                p.setLink(new Link(uri, "self"));
            }

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if(builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("{\"Error:\": \"Connection to Database failed.\"}")
                            .build();
        }
    }
    
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") int id) {
        Product p = null;
        try{
            p = pm.getProduct(id);
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("{\"Error:\": \"Connection to Database failed.\"}")
                            .build();
        }
        
        if(p != null){
            return Response.status(Status.OK).entity(p).build();
        } else{
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}
