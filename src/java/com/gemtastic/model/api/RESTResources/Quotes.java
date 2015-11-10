package com.gemtastic.model.api.RESTResources;

import com.gemtastic.model.managers.QuoteManager;
import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.resourcewrappers.QuoteWrapper;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * The quote resource is the heart of
 * @author Gemtastic
 */
@Path("/quotes")
public class Quotes {
    
    @EJB
    private QuoteManager qm;
    
    @Context
    private ResourceContext rc;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuotes(@Context UriInfo uriInfo, @Context Request request) {
        List<QuoteWrapper> quotes = null;
        
        try{
            quotes = qm.getAllQuotes();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(quotes != null){
            int hashValue = 0;
        
            for(QuoteWrapper qw : quotes){
                String uri = uriInfo.getBaseUriBuilder().path(Quote.class)
                                    .path(Integer.toString(qw.getQuote().getId()))
                                    .build()
                                    .toString();
                hashValue += qw.getQuote().hashCode();
                qw.setLink(new Link(uri, "self"));
            }

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if(builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"Error:\": \"Connection to Database failed.\"}").build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuote(Quote quote, @Context UriInfo uriInfo, @Context Request reques){
        
        QuoteWrapper q = null;
        try{
            q = qm.upsertQuote(quote);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(q != null){
            String uri = uriInfo.getBaseUriBuilder().path(Quotes.class)
                                .path(Integer.toString(q.getQuote().getId()))
                                .build()
                                .toString();
            
            q.setLink(new Link(uri, "Self"));
            
            return Response.status(Status.CREATED)
                            .entity(q)
                            .build(); 
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"Error:\": \"Connection to Database failed.\"}").build();
        }
    }
    
    @GET
    @Path("/{quoteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuote(@PathParam("quoteId") int id, @Context UriInfo uriInfo, @Context Request request) {
        QuoteWrapper quote = null;
        try{
            quote = qm.getQuoteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(quote != null) {
            int hashValue = 0;
        
            String uri = uriInfo.getBaseUriBuilder().path(Quotes.class)
                                    .path(Integer.toString(quote.getQuote().getId()))
                                    .build()
                                    .toString();

            hashValue += quote.getQuote().hashCode();

            quote.setLink(new Link(uri, "Self"));

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if(builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"Error:\": \"Connection to Database failed.\"}").build();
        }
    }
    
    @DELETE
    @Path("/{quoteId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteQuote(@PathParam("quoteId") int id){
        try{
            qm.deleteQuoteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"Error:\": \"Connection to Database failed.\"}").build();
        }
        
        return Response.status(Status.OK).build();
    }
    
    @PUT
    @Path("/{quoteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuote(@PathParam("quoteId") int id, @Context UriInfo uriInfo, Quote quote){
        try{
            QuoteWrapper original = qm.getQuoteById(id);
            if(original != null && quote.getId().equals(id)){
                QuoteWrapper qw = qm.upsertQuote(quote);

                String uri = uriInfo.getBaseUriBuilder().path(Quotes.class)
                                    .path(Integer.toString(qw.getQuote().getId()))
                                    .build()
                                    .toString();

                qw.setLink(new Link(uri, "Self"));

                return Response.status(Status.OK).entity(qw).build();
            } else {
                return Response.status(Status.BAD_REQUEST).build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"Error:\": \"Connection to Database failed.\"}").build();
        }
    }
    
    
    @GET
    @Path("/{quoteId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Comments getComments() {
        return rc.getResource(Comments.class);
    }
    
//    @GET
//    @Path("/test") 
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response test(){
//        
//        Quote q = new Quote();
//        Graphic g = new Graphic();
//        g.setFormat("format");
//        g.setId(1);
//        g.setPrice(0);
//        g.setQuote(q);
//        g.setSize(1);
//        g.setTimeest(0);
//        
//        GraphicWrapper gw = new GraphicWrapper();
//        gw.setGraphic(g);
//        gw.setLink(new Link("A", "B"));
//        List<ProductWrapper> pws = new ArrayList<>();
//        List<CommentWrapper> cws = new ArrayList<>();
//        List<ProductInterface> pl = new ArrayList<>();
//        
//        pl.add(gw);
//        ProductWrapper pw = new ProductWrapper(null, new Link("X", "self"));
//        pws.add(pw);
//        
//        QuoteWrapper quoteW = new QuoteWrapper(q, cws, pws, new Link("A", "self"));
//        return Response.status(Status.OK).entity(quoteW).build();
//    }
//    
//    private int generateHash(){
//        return 0;
//    }
}
