package web.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import web.core.IntegrationService;
import web.message.MessageIntegrationWrapper;
import web.model.IntegrationWebModel;
import web.model.PlatformWebModel;
import web.schema.FieldWebModel;

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
	public void postMessage(MessageIntegrationWrapper message) {
		service.postMessage(message);
	}

	@GET
	@Path("/fields/{label}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FieldWebModel> getFields(@PathParam("label") String platformLabel) {
		return service.getFields(platformLabel);
	}

	@POST
	@Path("/create")
	public void createOrEditIntegration(IntegrationWebModel integration) {
		service.createOrEditIntegration(integration);
	}

}
