import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Main {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)

    public  String hello(@QueryParam("name") String name){
        return String.format("Hello %s!",name);
    }
}
