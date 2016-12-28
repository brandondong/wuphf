package reddit.core;

import java.time.LocalDateTime;

class RedditToken {

	private final String accessToken;

	private final String refreshToken;

	private final LocalDateTime expiresIn;

	public RedditToken(String accessToken, String refreshToken, long epochSeconds) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		expiresIn = LocalDateTime.now().plusSeconds(epochSeconds);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiresIn);
	}

}
