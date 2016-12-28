package reddit.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class RedditIntegration implements Integration {

	private final String accessToken;

	public RedditIntegration(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public CompletableFuture<Optional<String>> message(String subject, String message, FieldValueMap receiver) {
		return new RedditOAuthService(accessToken)
				.sendMessage(receiver.getValueForField(RedditFields.USERNAME), subject, message)
				.thenApply((v) -> Optional.empty());
	}

}
