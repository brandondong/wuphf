package slack.core;

import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthAppDetails;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class SlackPlatform implements Platform {

	public static final OAuthAppDetails APP_DETAILS = new SlackAppDetails();

	@Override
	public String getLabel() {
		return "slack";
	}

	@Override
	public Fields getUserFields() {
		return SlackFields.getUserFields();
	}

	@Override
	public Fields getReceiverFields() {
		return SlackFields.getReceiverFields();
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new SlackIntegration(fieldValueMap.getValueForField(SlackFields.ACCESS_TOKEN));
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code) {
		return new SlackTokenRetriever().getAccessToken(code).thenCompose(this::retrieveIdentityInfo);
	}

	private CompletableFuture<FieldValueMap> retrieveIdentityInfo(String accessToken) {
		FieldValueMap.Builder valueMap = FieldValueMap.builder(getUserFields()).setField(SlackFields.ACCESS_TOKEN,
				accessToken);
		return new SlackOAuthService(accessToken).getTeamName()
				.thenApply((name) -> valueMap.setField(SlackFields.TEAM_NAME, name).create());
	}

	@Override
	public String getLogoImageLink() {
		return "slack-logo.png";
	}

	@Override
	public String getLoginRedirectUrl() {
		return String.format(
				"https://slack.com/oauth/authorize?client_id=%s&state=slack&scope=team:read,chat:write:user,im:read,users:read",
				APP_DETAILS.getClientId());
	}

}
