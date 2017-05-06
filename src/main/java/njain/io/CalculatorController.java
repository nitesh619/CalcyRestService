package njain.io;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/calculator")
public class CalculatorController {
    private AirthmeticService service = new AirthmeticService();

    @GET
    @Path("/{a}/{opr}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public String solvePath(@PathParam("a") int a,
                            @PathParam("opr") String  opr, @PathParam("b") int b) {
        return solveEquation(new Equation(a, b, opr.charAt(0)));
    }

    @GET
    @Path("query")
    @Produces(MediaType.APPLICATION_JSON)
    public String solveQueryEquation(@QueryParam("x") int a,
                                     @QueryParam("opr") String  opr,
                                     @QueryParam("y") int b) {
        return solveEquation(new Equation(a, b,opr.charAt(0)));
    }

    @GET
    @Path("/randomEq")
    @Produces(MediaType.APPLICATION_JSON)
    public Equation getRandomEquation() {
        return service.createRandomEquation();
    }

    @POST
    @Path("/solveEquation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String solveEquation(Equation equation) {
        return equation.toString() + String.valueOf(service.solveEquation(equation));
    }

    @GET
    @Path("/clientInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String getClientInformation(@Context UriInfo uriInfo,
                                     @Context HttpHeaders headers) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nURI: "+uriInfo.getAbsolutePath());
        builder.append("\nHas query: "+ uriInfo.getQueryParameters());
        builder.append("\nHeader Language: "+ headers.getLanguage());
        builder.append("\nHeader MIME: " + headers.getMediaType());
        builder.append("\nCookies: " + headers.getCookies().toString());
        return builder.toString();
    }

    @POST
    @Path("/echo")
    public Response createEcho(Equation equation) throws URISyntaxException {
        return Response.status(Response.Status.CREATED).
                entity(equation).contentLocation(new URI("http://www.google.com")).build();
    }

    // Sub resources
    @Path("/trigno")
    public TrignometryController trignometry() {
        return new TrignometryController();
    }
}
