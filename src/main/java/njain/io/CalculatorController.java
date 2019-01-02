package njain.io;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/calculator")
public class CalculatorController {

    private AirthmeticService service = new AirthmeticService();

    //taking data from path
    @GET
    @Path("/{a}/{opr}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public String solvePath(@PathParam("a") int a,
            @PathParam("opr") String  opr, @PathParam("b") int b) {
        return solveEquation(new Equation(a, b, opr.charAt(0)));
    }

    //taking data from query
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

    //taking data from JSON body
    @POST
    @Path("/solveEquation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String solveEquation(Equation equation) {
        return equation.toString() + String.valueOf(service.solveEquation(equation));
    }

    @GET
    @Path("/solveEquation/{eqn}")
    @Produces(MediaType.TEXT_PLAIN)
    public String solvePathEquation(@PathParam("eqn") Equation equation) {
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
