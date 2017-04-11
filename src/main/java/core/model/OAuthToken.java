package core.model;

import java.time.LocalDateTime;

public class OAuthToken {

	protected final String accessToken;

	protected final LocalDateTime expiresAt;

	protected OAuthToken(String accessToken, LocalDateTime expiresAt) {
		this.accessToken = accessToken;
		this.expiresAt = expiresAt;
	}

	protected OAuthToken(String accessToken, long expiresIn) {
		this(accessToken, LocalDateTime.now().plusSeconds(expiresIn));
	}

	public static OAuthToken expiresIn(String accessToken, long epochSeconds) {
		return new OAuthToken(accessToken, epochSeconds);
	}

	public static OAuthToken expiresAt(String accessToken, LocalDateTime expiresAt) {
		return new OAuthToken(accessToken, expiresAt);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiresAt);
	}

	public String getExpiryDate() {
		return expiresAt.toString();
	}

}