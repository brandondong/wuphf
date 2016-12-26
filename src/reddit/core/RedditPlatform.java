package reddit.core;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;
import util.core.APIKeyReader;

public class RedditPlatform implements Platform {

	public static final String CLIENT_ID = new APIKeyReader().getKeyFromFile("reddit_client_id");

	public static final String CLIENT_SECRET = new APIKeyReader().getKeyFromFile("reddit_client_secret");

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(Map<String, String> properties) {
		return new RedditTokenRetriever().getToken(properties).thenCompose(this::createMapWithToken);
	}

	private CompletableFuture<FieldValueMap> createMapWithToken(String accessToken) {
		FieldValueMap.Builder mapBuilder = FieldValueMap.builder(getUserFields()).setField(RedditFields.ACCESS_TOKEN,
				accessToken);
		// TODO retrieve username info
		return CompletableFuture.completedFuture(mapBuilder.create());
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
