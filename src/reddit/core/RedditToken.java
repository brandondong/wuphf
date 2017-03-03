package reddit.core;

import java.time.LocalDateTime;

import core.model.OAuthToken;

class RedditToken extends OAuthToken {

	private final String refreshToken;

	private RedditToken(String accessToken, String refreshToken, LocalDateTime expiresAt) {
		super(accessToken, expiresAt);
		this.refreshToken = refreshToken;
	}

	private RedditToken(String accessToken, String refreshToken, long expiresIn) {
		super(accessToken, expiresIn);
		this.refreshToken = refreshToken;
	}

	public static RedditToken expiresIn(String accessToken, String refreshToken, long epochSeconds) {
		return new RedditToken(accessToken, refreshToken, epochSeconds);
	}

	public static RedditToken expiresAt(String accessToken, String refreshToken, LocalDateTime expiresAt) {
		return new RedditToken(accessToken, refreshToken, expiresAt);
	}

	public String getRefreshToken() {
		return refreshToken;
	}

}
