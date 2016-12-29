package web.core;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import core.model.IPlatformManager;
import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import web.message.MessageIntegrationWrapper;
import web.model.IntegrationWebModel;
import web.model.PlatformWebModel;
import web.model.RedirectProperties;

/**
 * The implementation class to handle web requests related to platforms and
 * integrations
 */
public class IntegrationService {

	private final IPlatformManager manager;

	public IntegrationService(IPlatformManager manager) {
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
	public CompletableFuture<IntegrationWebModel> postMessage(MessageIntegrationWrapper message) {
		Platform platform = manager.getPlatformByLabel(message.getIntegration().getPlatformLabel());
		FieldValueMap receiver = ReceiverConverter.from(message.getReceiver(), manager).convert();
		Integration integration = IntegrationConverter.from(message.getIntegration(), manager).convert();
		return integration.message(message.getSubject(), message.getMessage(), receiver).thenApply((opfVM) -> {
			return opfVM.map((fvm) -> IntegrationWebModel.createFrom(fvm, platform)).orElse(message.getIntegration());
		});
	}

	public CompletableFuture<IntegrationWebModel> createIntegrationFromRedirect(RedirectProperties properties) {
		Platform platform = manager.getPlatformByLabel(properties.getPlatformLabel());
		return platform.createIntegrationFromRedirect(properties.getProperties())
				.thenApply((fvm) -> IntegrationWebModel.createFrom(fvm, platform));
	}

}
