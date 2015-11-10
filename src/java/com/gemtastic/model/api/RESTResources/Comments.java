package com.gemtastic.model.api.RESTResources;

import com.gemtastic.model.entities.Link;
import com.gemtastic.model.entities.database.Comment;
import com.gemtastic.model.entities.resourcewrappers.CommentWrapper;
import com.gemtastic.model.managers.CommentManager;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Gemtastic
 */
@Path("/comments")
public class Comments {

    @EJB
    CommentManager cm;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments(@PathParam("quoteId") int quoteId,
            @Context UriInfo uriInfo,
            @Context Request request) {
        System.out.println(quoteId);
        List<CommentWrapper> comments = null;
        try {
            comments = cm.getAllCommentsOff(quoteId);
        } catch (Exception e) {
            e.printStackTrace();

        }

        if (comments != null) {
            int hashValue = 0;

            for (CommentWrapper cw : comments) {

                String uri = uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .path(Integer.toString(cw.getComment().getId()))
                        .build()
                        .toString();
                hashValue += cw.getComment().hashCode();

                cw.setLink(new Link(uri, "self"));
            }

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            Response.ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if (builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();

        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"Error:\": \"Connection to Database failed.\", \"URI\": \"" + uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .path(Integer.toString(quoteId))
                        .build()
                        .toString() + "\"}")
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(@Context Request request, @Context UriInfo uriInfo, Comment comment) {
        CommentWrapper c = null;
        try {
            c = cm.createComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c != null) {
            int hashValue = c.hashCode();

            String uri = uriInfo.getBaseUriBuilder()
                    .path(Comments.class)
                    .path(Integer.toString(c.getComment().getId()))
                    .build()
                    .toString();

            c.setLink(new Link(uri, "self"));

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            Response.ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if (builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"Error:\": \"Connection to Database failed.\", \"URI\": \"" + uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .build()
                        .toString() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("commentId") int id, @Context Request request, @Context UriInfo uriInfo) {
        CommentWrapper c = null;
        System.out.println("Id: " + id);

        try {
            c = cm.getComment(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c != null) {
            int hashValue = c.hashCode();

            String uri = uriInfo.getBaseUriBuilder()
                    .path(Comments.class)
                    .path(Integer.toString(c.getComment().getId()))
                    .build()
                    .toString();

            c.setLink(new Link(uri, "self"));

            EntityTag eTag = new EntityTag(Integer.toString(hashValue));
            Response.ResponseBuilder builder = request.evaluatePreconditions(eTag);

            if (builder == null) {
                builder = Response.status(Status.OK);
                builder.tag(eTag);
            }
            return builder.build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"Error:\": \"Connection to Database failed.\", \"URI\": \"" + uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .path(Integer.toString(id))
                        .build()
                        .toString() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateComment(@PathParam("commentId") int id, @Context UriInfo uriInfo, Comment comment) {
        CommentWrapper c = null;
        try {
            if(comment.getId().equals(id)){
                c = cm.updateComment(comment);
            } else {
                Response.status(Status.BAD_REQUEST).build();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(c != null){
            String uri = uriInfo.getBaseUriBuilder()
                    .path(Comments.class)
                    .path(Integer.toString(c.getComment().getId()))
                    .build()
                    .toString();

            c.setLink(new Link(uri, "self"));
            
            return Response.status(Status.OK).entity(c).build();
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"Error:\": \"Connection to Database failed.\", \"URI\": \"" + uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .path(Integer.toString(id))
                        .build()
                        .toString() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("commentId") int id, @Context UriInfo uriInfo) {
        try {
            cm.deleteComment(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"Error:\": \"Connection to Database failed.\", \"URI\": \"" + uriInfo.getBaseUriBuilder()
                        .path(Comments.class)
                        .path(Integer.toString(id))
                        .build()
                        .toString() + "\"}")
                    .build();
        }
        return Response.status(Status.OK).build();
    }
}
