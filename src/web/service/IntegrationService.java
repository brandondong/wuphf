package web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import core.model.IntegrationManager;
import web.model.IntegrationWebModel;
import web.model.PlatformWebModel;

/**
 * The implementation class to handle web requests related to platforms and
 * integrations
 */
public class IntegrationService {

	/**
	 * 
	 * @return a list of all possible platforms that can be created
	 */
	public List<PlatformWebModel> getAllPlatforms() {
		return IntegrationManager.instance().getAllPlatforms().stream().map(PlatformWebModel::createFrom)
				.collect(Collectors.toList());
	}

	public List<IntegrationWebModel> getAllIntegrations() {
		return new ArrayList<>();
	}

}
