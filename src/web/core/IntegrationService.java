package web.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import core.model.IntegrationManager;
import core.model.Platform;
import core.schema.FieldValueMap;
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
		return IntegrationManager.instance().getAllIntegrations().stream().map(IntegrationWebModel::createFrom)
				.collect(Collectors.toList());
	}

	/**
	 * Posts a status message on all configured integrations
	 * 
	 * @param message
	 *            the message to be posted
	 */
	public void postMessage(String message) {
		IntegrationManager.instance().getAllIntegrations().forEach(i -> i.post(message));
	}

	public void createOrEditIntegration(IntegrationWebModel integration) {
		Platform platform = IntegrationManager.instance().getPlatformByLabel(integration.getPlatform().getLabel());
		FieldValueMap map = FieldValueMap.createWith(platform.getFields(), integration.getValueMap());
		IntegrationManager.instance().createOrEditIntegration(Optional.ofNullable(integration.getLabel()), platform,
				map);
	}

}
