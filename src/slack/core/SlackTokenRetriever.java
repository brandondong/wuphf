package slack.core;

import java.util.concurrent.CompletableFuture;

class SlackTokenRetriever {

	public CompletableFuture<String> getAccessToken(String code) {
		return CompletableFuture.completedFuture(code);
	}

}
