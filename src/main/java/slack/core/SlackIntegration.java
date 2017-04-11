package slack.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class SlackIntegration implements Integration {

	private final SlackOAuthService service;

	public SlackIntegration(String accessToken) {
		service = new SlackOAuthService(accessToken);
	}

	@Override
	public CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver) {
		String receiverName = receiver.getValueForField(SlackFields.NAME);
		return service.findUserIdOfUser(receiverName).thenCompose(service::findChannelIdOfIMUser)
				.thenCompose((id) -> service.sendMessage(id, message)).thenApply((v) -> Optional.empty());
	}

}
