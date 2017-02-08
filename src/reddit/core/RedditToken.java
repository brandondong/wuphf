package reddit.core;

import java.time.LocalDateTime;

class RedditToken {

	private final String accessToken;

	private final String refreshToken;

	private final LocalDateTime expiresAt;

	private RedditToken(String accessToken, String refreshToken, long epochSeconds) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		expiresAt = LocalDateTime.now().plusSeconds(epochSeconds);
	}

	private RedditToken(String accessToken, String refreshToken, LocalDateTime expiresAt) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
	}

	public static RedditToken expiresIn(String accessToken, String refreshToken, long epochSeconds) {
		return new RedditToken(accessToken, refreshToken, epochSeconds);
	}

	public static RedditToken expiresAt(String accessToken, String refreshToken, LocalDateTime expiresAt) {
		return new RedditToken(accessToken, refreshToken, expiresAt);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiresAt);
	}

	public String getExpiryDate() {
		return expiresAt.toString();
	}

}
