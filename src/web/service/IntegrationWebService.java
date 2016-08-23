package web.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.core.IntegrationService;
import web.model.IntegrationWebModel;
import web.model.PlatformWebModel;

/**
 * Service for handling platform and integration related web requests
 */
@Path("/integration")
public class IntegrationWebService {

	private final IntegrationService service = new IntegrationService();

	@GET
	@Path("/platforms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlatformWebModel> getAllPlatforms() {
		return service.getAllPlatforms();
	}

	@GET
	@Path("/integrations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IntegrationWebModel> getAllIntegrations() {
		return service.getAllIntegrations();
	}

	@POST
	@Path("/post")
	public void postMessage(String message) {
		service.postMessage(message);
	}

	@POST
	@Path("/create")
	public void createOrEditIntegration(IntegrationWebModel integration) {
		service.createOrEditIntegration(integration);
	}

}
