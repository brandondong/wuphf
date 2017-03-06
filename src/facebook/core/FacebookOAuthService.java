package facebook.core;

import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import core.model.OAuthToken;
import web.service.HttpService;

class FacebookOAuthService {

	private final String accessToken;

	public FacebookOAuthService(OAuthToken token) {
		accessToken = token.getAccessToken();
	}

	public CompletableFuture<String> getUserFullname() {
		String url = String.format("https://graph.facebook.com/v2.8/me?access_token=%s", accessToken);
		HttpGet get = new HttpGet(url);
		return new HttpService().getResponse(get).thenApply((s) -> {
			try {
				return new FacebookResponseParser().parse(s).getString("name");
			} catch (JSONException e) {
				throw new IllegalStateException(e);
			}
		});
	}

}
