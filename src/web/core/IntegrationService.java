package web.core;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.PlatformManager;
import core.schema.FieldValueMap;
import web.message.MessageIntegrationWrapper;
import web.model.PlatformWebModel;

/**
 * The implementation class to handle web requests related to platforms and
 * integrations
 */
public class IntegrationService {

	private static final String DEFAULT_COMPLETION_MESSAGE = "Message sent successfully.";

	private final PlatformManager manager;

	public IntegrationService(PlatformManager manager) {
		this.manager = manager;
	}

	/**
	 * @return a list of all possible platforms that can be created
	 */
	public List<PlatformWebModel> getAllPlatforms() {
		return manager.getAllPlatforms().stream().map(PlatformWebModel::createFrom).collect(toList());
	}

	/**
	 * Posts a status message on all configured integrations
	 * 
	 * @param message
	 *            the message to be posted
	 */
	public CompletableFuture<String> postMessage(MessageIntegrationWrapper message) {
		FieldValueMap receiver = ReceiverConverter.from(message.getReceiver(), manager).convert();
		Integration integration = IntegrationConverter.from(message.getIntegration(), manager).convert();
		return integration.message(message.getMessage(), receiver)
				.thenApply((opStr) -> opStr.orElse(DEFAULT_COMPLETION_MESSAGE));
	}

}
