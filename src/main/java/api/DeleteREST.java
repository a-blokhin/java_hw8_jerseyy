package api;

import DAO.ProductDAO;
import commons.Connect;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/deleteProduct")
public class DeleteREST {

    @POST
    @Path("/")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteByName(String name){
        Connection connection = null;
        try {
            connection = Connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDAO productDAO = new ProductDAO(connection);
        if(productDAO.deleteByName(name)){
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
