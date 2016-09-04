package web.core;

import static java.util.stream.Collectors.toList;

import java.util.List;

import core.model.PlatformManager;
import web.message.MessageIntegrationWrapper;
import web.model.PlatformWebModel;

/**
 * The implementation class to handle web requests related to platforms and
 * integrations
 */
public class IntegrationService {

	/**
	 * @return a list of all possible platforms that can be created
	 */
	public List<PlatformWebModel> getAllPlatforms() {
		return PlatformManager.instance().getAllPlatforms().stream().map(PlatformWebModel::createFrom)
				.collect(toList());
	}

	/**
	 * Posts a status message on all configured integrations
	 * 
	 * @param message
	 *            the message to be posted
	 */
	public void postMessage(MessageIntegrationWrapper message) {
		message.getIntegrations().stream().map(i -> IntegrationConverter.from(i).convert())
				.forEach(i -> i.post(message.getMessage()));
	}

}
