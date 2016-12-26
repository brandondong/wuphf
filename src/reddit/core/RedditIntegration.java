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
	public CompletableFuture<Optional<String>> message(String message, FieldValueMap receiver) {
		// TODO Auto-generated method stub
		return CompletableFuture.completedFuture(Optional.of(accessToken));
	}

}
