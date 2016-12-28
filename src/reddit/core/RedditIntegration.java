package reddit.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class RedditIntegration implements Integration {

	private final RedditToken accessToken;

	public RedditIntegration(FieldValueMap valueMap) {
		accessToken = RedditToken.expiresAt(valueMap.getValueForField(RedditFields.ACCESS_TOKEN),
				valueMap.getValueForField(RedditFields.REFRESH_TOKEN),
				valueMap.getValueForField(RedditFields.EXPIRES_AT));
	}

	@Override
	public CompletableFuture<Optional<String>> message(String subject, String message, FieldValueMap receiver) {
		return new RedditOAuthService(accessToken.getAccessToken())
				.sendMessage(receiver.getValueForField(RedditFields.USERNAME), subject, message)
				.thenApply((v) -> Optional.empty());
	}

}
