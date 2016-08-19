package web.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.model.IntegrationResource;
import web.model.PlatformResource;

/**
 * Service for handling platform and integration related web requests
 */
@Path("/integration")
public class IntegrationWebService {

	private final IntegrationService service = new IntegrationService();

	@GET
	@Path("/platforms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlatformResource> getAllPlatforms() {
		return service.getAllPlatforms();
	}

	@GET
	@Path("/integrations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IntegrationResource> getAllIntegrations() {
		return service.getAllIntegrations();
	}

}
