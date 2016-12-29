package reddit.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class RedditIntegration implements Integration {

	private final RedditToken accessToken;

	private final String username;

	public RedditIntegration(FieldValueMap valueMap) {
		username = valueMap.getValueForField(RedditFields.USERNAME);
		accessToken = RedditToken.expiresAt(valueMap.getValueForField(RedditFields.ACCESS_TOKEN),
				valueMap.getValueForField(RedditFields.REFRESH_TOKEN),
				valueMap.getValueForField(RedditFields.EXPIRES_AT));
	}

	@Override
	public CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver) {
		CompletableFuture<RedditToken> future = accessToken.isExpired()
				? new RedditTokenRetriever().refreshToken(accessToken) : CompletableFuture.completedFuture(accessToken);
		return future.thenCompose((t) -> messageWithToken(t, subject, message, receiver));
	}

	private CompletableFuture<Optional<FieldValueMap>> messageWithToken(RedditToken token, String subject,
			String message, FieldValueMap receiver) {
		FieldValueMap valueMap = FieldValueMap.builder(RedditFields.getUserFields())
				.setField(RedditFields.USERNAME, username).setField(RedditFields.ACCESS_TOKEN, token.getAccessToken())
				.setField(RedditFields.REFRESH_TOKEN, token.getRefreshToken())
				.setField(RedditFields.EXPIRES_AT, token.getExpiryDate()).create();
		return new RedditOAuthService(token)
				.sendMessage(receiver.getValueForField(RedditFields.USERNAME), subject, message)
				.thenApply((v) -> Optional.of(valueMap));
	}

}
