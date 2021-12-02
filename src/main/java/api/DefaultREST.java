package api;

import com.google.common.io.Resources;
import org.eclipse.jetty.security.LoginService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Path("/info")
public class DefaultREST {

    @GET
    @Produces("text/html")
    public String DefaultPage() {
        String text = null;
        try {
            text = Resources.toString(LoginService.class.getResource("/static/info"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
