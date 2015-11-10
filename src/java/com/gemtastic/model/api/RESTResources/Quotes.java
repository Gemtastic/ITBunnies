package com.gemtastic.model.api.RESTResources;

import com.gemtastic.model.managers.QuoteManager;
import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.CommentWrapper;
import com.gemtastic.model.entities.resourcewrappers.ProductWrapper;
import com.gemtastic.model.entities.resourcewrappers.QuoteWrapper;
import com.gemtastic.model.entities.resourcewrappers.interfaces.ProductInterface;
import com.gemtastic.model.entities.resourcewrappers.products.GraphicWrapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
 *
 * @author Gemtastic
 */
@Path("/")
public class Quotes {
    
    @EJB
    private QuoteManager qm;
    
    @GET
    @Path("/quotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuotes(@Context UriInfo uriInfo, @Context Request request) {
        List<QuoteWrapper> quotes = qm.getAllQuotes();
        int hashValue = 0;
        
        for(QuoteWrapper qw : quotes){
            String uri = uriInfo.getBaseUriBuilder().path(Quote.class)
                                .path(Integer.toString(qw.getQuote().getId()))
                                .build()
                                .toString();
            hashValue += qw.getQuote().hashCode();
        }
        
        EntityTag eTag = new EntityTag(Integer.toString(hashValue));
        ResponseBuilder builder = request.evaluatePreconditions(eTag);
        
        if(builder == null) {
            builder = Response.status(Status.OK);
            builder.tag(eTag);
        }
        return builder.build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuote(@PathParam("id") int id) {
        QuoteWrapper quote = qm.getQuoteById(id);
        
        return Response.status(Status.OK)
                        .entity(quote)
                        .build();
    }
    

    
    @POST
    @Path("/quotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuote(Quote quote){
        
        return null;
    }
    
    @DELETE
    @Path("/quotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteQuote(Quote quote){
        
        return null;
    }
    
    @PUT
    @Path("/quotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuote(Quote quote){
        
        return null;
    }
    
    
    @GET
    @Path("/test") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(){
        
        Quote q = new Quote();
        Graphic g = new Graphic();
        g.setFormat("format");
        g.setId(1);
        g.setPrice(0);
        g.setQuote(q);
        g.setSize(1);
        g.setTimeest(0);
        
        GraphicWrapper gw = new GraphicWrapper();
        gw.setGraphic(g);
        gw.setLink(new Link("A", "B"));
        List<ProductWrapper> pws = new ArrayList<>();
        List<CommentWrapper> cws = new ArrayList<>();
        List<ProductInterface> pl = new ArrayList<>();
        
        pl.add(gw);
        ProductWrapper pw = new ProductWrapper(null, new Link("X", "self"));
        pws.add(pw);
        
        QuoteWrapper quoteW = new QuoteWrapper(q, cws, pws, new Link("A", "self"));
        return Response.status(Status.OK).entity(quoteW).build();
    }
    
    private int generateHash(){
        return 0;
    }
}
