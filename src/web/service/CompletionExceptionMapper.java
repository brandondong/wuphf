package web.service;

import java.util.concurrent.CompletionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CompletionExceptionMapper implements ExceptionMapper<CompletionException> {

	@Override
	public Response toResponse(CompletionException e) {
		return Response.status(Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
	}

}
