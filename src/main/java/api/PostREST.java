package api;

import DAO.ProductDAO;
import commons.Connect;
import entity.Product;

import javax.servlet.http.HttpServletResponse;
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
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteByName(String name, String company, String quantityString){
        Connection connection = null;
        try {
            connection = Connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDAO productDAO = new ProductDAO(connection);
        if ((name == null)|(company == null)|(quantityString == null)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int quantity = Integer.parseInt(quantityString);
        productDAO.save(new Product(name,
                company,
                quantity));
        return Response.ok().build();
    }
}