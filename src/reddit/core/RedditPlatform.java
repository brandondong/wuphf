package reddit.core;

import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthAppDetails;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class RedditPlatform implements Platform {

	public static final OAuthAppDetails APP_DETAILS = new RedditAppDetails();

	@Override
	public String getLabel() {
		return "reddit";
	}

	@Override
	public Fields getUserFields() {
		return RedditFields.getUserFields();
	}

	@Override
	public Fields getReceiverFields() {
		return RedditFields.getReceiverFields();
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new RedditIntegration(fieldValueMap);
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code) {
		return new RedditTokenRetriever().getToken(code).thenCompose(this::createMapWithToken);
	}

	private CompletableFuture<FieldValueMap> createMapWithToken(RedditToken token) {
		FieldValueMap.Builder mapBuilder = FieldValueMap.builder(getUserFields())
				.setField(RedditFields.ACCESS_TOKEN, token.getAccessToken())
				.setField(RedditFields.REFRESH_TOKEN, token.getRefreshToken())
				.setField(RedditFields.EXPIRES_AT, token.getExpiryDate());
		return new RedditOAuthService(token).getUsername()
				.thenApply((u) -> mapBuilder.setField(RedditFields.USERNAME, u).create());
	}

	@Override
	public String getLogoImageLink() {
		return "reddit-logo.png";
	}

	@Override
	public String getLoginRedirectUrl() {
		return String.format(
				"https://www.reddit.com/api/v1/authorize?client_id=%s&response_type=code&state=reddit&redirect_uri=%s&duration=permanent&scope=identity privatemessages",
				APP_DETAILS.getClientId(), Platform.APP_REDIRECT);
	}

}
