package facebook.core;

import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthAppDetails;
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
		// TODO Auto-generated method stub
		return null;
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

}
