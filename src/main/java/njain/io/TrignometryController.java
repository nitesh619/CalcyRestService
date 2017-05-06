package njain.io;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by nitesh.jain on 04-05-2017.
 */
@Path("")
public class TrignometryController {

    @GET
    @Path("/sin/{Q}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sinValue(@PathParam("Q") float angle) {
        return String.valueOf(Math.sin(Math.toRadians(angle)));
    }
}
