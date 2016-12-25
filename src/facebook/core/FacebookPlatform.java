package facebook.core;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class FacebookPlatform implements Platform {

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
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(Map<String, String> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogoImageLink() {
		return "facebook-logo.png";
	}

	@Override
	public String getLoginRedirectUrl() {
		// TODO register the app and acquire this
		return "";
	}

}
