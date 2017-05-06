package njain.io.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by nitesh.jain on 05-05-2017.
 */
@Provider
public class CalculatorExceptionMapper implements ExceptionMapper<UnsupportedOperatorException> {

    @Override
    public Response toResponse(UnsupportedOperatorException e) {
        return Response.status(Response.Status.NOT_FOUND).
                entity(new ErrorMessage(e.getMessage(), Response.Status.NOT_FOUND.getStatusCode(), "Ye operator available nhi hai!"))
                .build();
    }
}
