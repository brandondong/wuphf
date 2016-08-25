package web.core;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import core.model.IntegrationManager;
import core.model.Platform;
import core.schema.FieldValueMap;
import web.model.IntegrationWebModel;
import web.model.PlatformWebModel;
import web.schema.FieldWebModel;

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
				.collect(toList());
	}

	public List<IntegrationWebModel> getAllIntegrations() {
		return IntegrationManager.instance().getAllIntegrations().stream().map(IntegrationWebModel::createFrom)
				.collect(toList());
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
		FieldValueMap map = FieldValueMap.createWith(platform.getFields(), convertToMap(integration.getValueMap()));
		IntegrationManager.instance().createOrEditIntegration(integration.getIntegrationLabel(), platform, map);
	}

	private Map<String, String> convertToMap(Map<FieldWebModel, String> valueMap) {
		return valueMap.keySet().stream().collect(toMap(f -> f.getLabel(), f -> valueMap.get(f)));
	}

}
