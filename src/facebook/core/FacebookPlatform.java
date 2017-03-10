package facebook.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthAppDetails;
import core.model.OAuthToken;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class FacebookPlatform implements Platform {

	public static final OAuthAppDetails APP_DETAILS = new FacebookAppDetails();

	@Override
	public String getLabel() {
		return "facebook";
	}

	@Override
	public Fields getUserFields() {
		return FacebookFields.getUserFields();
	}

	@Override
	public Fields getReceiverFields() {
		return FacebookFields.getReceiverFields();
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new FacebookIntegration(fieldValueMap);
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code) {
		return new FacebookTokenRetriever().retrieveToken(code).thenCompose(this::createIntegrationWithToken);
	}

	private CompletableFuture<FieldValueMap> createIntegrationWithToken(OAuthToken token) {
		FieldValueMap.Builder map = FieldValueMap.builder(getUserFields())
				.setField(FacebookFields.ACCESS_TOKEN, token.getAccessToken())
				.setField(FacebookFields.EXPIRES_AT, token.getExpiryDate());
		return new FacebookOAuthService(token).getUserFullname()
				.thenApply((name) -> map.setField(FacebookFields.NAME, name).create());
	}

	@Override
	public String getLogoImageLink() {
		return "facebook-logo.png";
	}

	@Override
	public String getLoginRedirectUrl() {
		return String.format("https://www.facebook.com/v2.8/dialog/oauth?client_id=%s&redirect_uri=%s&state=facebook",
				APP_DETAILS.getClientId(), Platform.APP_REDIRECT);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of(
				"The Facebook API currently does not support sending private messages. Messages sent through Wuphf will instead be posted to the user's profile feed.");
	}

}
