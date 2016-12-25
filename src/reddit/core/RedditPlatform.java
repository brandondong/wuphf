package reddit.core;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;
import util.core.APIKeyReader;

public class RedditPlatform implements Platform {

	private static final String CLIENT_ID = new APIKeyReader().getKeyFromFile("reddit_client_id");

	private static final String CLIENT_SECRET = new APIKeyReader().getKeyFromFile("reddit_client_secret");

	@Override
	public String getLabel() {
		return "reddit";
	}

	@Override
	public Fields getUserFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fields getReceiverFields() {
		return RedditFields.getReceiverFields();
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(Map<String, String> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogoImageLink() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLoginRedirectUrl() {
		return String.format(
				"https://www.reddit.com/api/v1/authorize?client_id=%s&response_type=code&state=wuphf&redirect_uri=%s&duration=permanent&scope=identity privatemessages",
				CLIENT_ID, Platform.APP_REDIRECT);
	}

}
