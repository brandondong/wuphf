package reddit.core;

import java.util.concurrent.CompletableFuture;

public class RedditOAuthService {

	private final String accessToken;

	public RedditOAuthService(String accessToken) {
		this.accessToken = accessToken;
	}

	public CompletableFuture<String> getUsername() {
		return CompletableFuture.completedFuture(accessToken);
	}

}
