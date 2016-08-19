package web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import core.model.IntegrationManager;
import web.model.IntegrationResource;
import web.model.PlatformResource;

/**
 * The implementation class to handle web requests related to platforms and
 * integrations
 */
public class IntegrationService {

	/**
	 * 
	 * @return a list of all possible platforms that can be created
	 */
	public List<PlatformResource> getAllPlatforms() {
		return IntegrationManager.instance().getAllPlatforms().stream().map(PlatformResource::createFrom)
				.collect(Collectors.toList());
	}

	public List<IntegrationResource> getAllIntegrations() {
		return new ArrayList<>();
	}

}
