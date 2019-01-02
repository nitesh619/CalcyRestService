package njain.io.Filter;

import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.Base64;

/**
 * Created by nitesh.jain on 09-05-2017.
 */
//@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BASIC = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        //Basic auth on every resource of calcy API
        List<String> authorization = requestContext.getHeaders().get(AUTHORIZATION);

        if (authorization != null && !authorization.isEmpty()) {
            String authToken = authorization.get(0);
            authToken = authToken.replaceFirst(BASIC, "");

            String decodeString = Base64.decodeAsString(authToken);

            StringTokenizer stringTokenizer = new StringTokenizer(decodeString, ":");
            String userName = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();

            if (userName.equals("johnCena") && password.equals("wwe")) {
                return;
            }
        }
        Response unauthorisedResponse = Response.status(Response.Status.UNAUTHORIZED)
                .entity("you are not authorize").build();
        requestContext.abortWith(unauthorisedResponse);
    }
}
