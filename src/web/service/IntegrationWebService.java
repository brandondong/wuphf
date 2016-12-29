package web.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import core.model.PlatformManager;
import web.core.IntegrationService;
import web.message.MessageIntegrationWrapper;
import web.model.PlatformWebModel;
import web.model.RedirectProperties;

/**
 * Service for handling platform and integration related web requests
 */
@Path("/integration")
public class IntegrationWebService {

	private final IntegrationService service = new IntegrationService(PlatformManager.instance());

	@GET
	@Path("/platforms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlatformWebModel> getAllPlatforms() {
		return service.getAllPlatforms();
	}

	@POST
	@Path("/message")
	public void postMessage(MessageIntegrationWrapper message, @Suspended AsyncResponse response) {
		service.postMessage(message).thenApply((i) -> response.resume(i)).exceptionally((t) -> response.resume(t));
	}

	@POST
	@Path("/create")
	public void createIntegrationFromRedirect(RedirectProperties properties, @Suspended AsyncResponse response) {
		service.createIntegrationFromRedirect(properties).thenApply((i) -> response.resume(i))
				.exceptionally((t) -> response.resume(t));
	}

}
