package facebook.core;

import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import core.model.OAuthAppDetails;
import core.model.OAuthToken;
import core.model.Platform;
import web.service.HttpService;

class FacebookTokenRetriever {

	public CompletableFuture<OAuthToken> retrieveToken(String code) {
		OAuthAppDetails details = FacebookPlatform.APP_DETAILS;
		String tokenUrl = String.format(
				"https://graph.facebook.com/v2.8/oauth/access_token?client_id=%s&redirect_uri=%s&client_secret=%s&code=%s",
				details.getClientId(), Platform.APP_REDIRECT, details.getClientSecret(), code);
		HttpGet get = new HttpGet(tokenUrl);
		return new HttpService().getResponse(get).thenApply(this::parseResponse);
	}

	private OAuthToken parseResponse(String content) {
		try {
			JSONObject json = new JSONObject(content);
			String accessToken = json.getString("access_token");
			long expiresIn = json.getLong("expires_in");
			return OAuthToken.expiresIn(accessToken, expiresIn);
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

}
