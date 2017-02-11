package slack.core;

import java.util.concurrent.CompletableFuture;

class SlackOAuthService {

	private final String accessToken;

	public SlackOAuthService(String accessToken) {
		this.accessToken = accessToken;
	}

	public CompletableFuture<String> getTeamName() {
		return CompletableFuture.completedFuture(accessToken);
	}

}
