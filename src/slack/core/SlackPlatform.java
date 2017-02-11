package slack.core;

import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthAppDetails;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class SlackPlatform implements Platform {

	private static final OAuthAppDetails APP_DETAILS = new SlackAppDetails();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogoImageLink() {
		return "slack-logo.png";
	}

	@Override
	public String getLoginRedirectUrl() {
		return String.format("https://slack.com/oauth/authorize?client_id=%s&state=slack&scope=identity.basic",
				APP_DETAILS.getClientId());
	}

}
