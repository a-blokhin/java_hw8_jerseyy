package api;

import DAO.ProductDAO;
import commons.Connect;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/getProducts")
public class GetREST {

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        Connection connection = null;
        try {
            connection = Connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDAO productDAO = new ProductDAO(connection);
        return Response.ok(productDAO.all())
                .build();
    }

    @Path("/{company}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCompany(@PathParam("company")String company){
        Connection connection = null;
        try {
            connection = Connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDAO productDAO = new ProductDAO(connection);
        return Response.ok(productDAO.getByCompany(company)).build();
    }
}
