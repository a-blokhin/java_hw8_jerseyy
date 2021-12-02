package api;

import DAO.ProductDAO;
import commons.Connect;
import entity.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/postProduct")
public class PostREST {
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteByName(Product product) {
        Connection connection = null;
        try {
            connection = Connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDAO productDAO = new ProductDAO(connection);
        if (productDAO.save(product)){
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Id is already taken").build();
        }

    }
}