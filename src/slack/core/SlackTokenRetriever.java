package slack.core;

import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import core.model.OAuthAppDetails;
import web.service.HttpService;

class SlackTokenRetriever {

	public CompletableFuture<String> getAccessToken(String code) {
		OAuthAppDetails details = SlackPlatform.APP_DETAILS;
		String tokenUrl = String.format("https://slack.com/api/oauth.access?client_id=%s&client_secret=%s&code=%s",
				details.getClientId(), details.getClientSecret(), code);
		HttpGet httpGet = new HttpGet(tokenUrl);
		return new HttpService().getResponse(httpGet).thenApply(this::parseResponse);
	}

	private String parseResponse(String content) {
		try {
			JSONObject jsonResponse = new JSONObject(content);
			new SlackResponseVerifier().checkNoErrors(jsonResponse);
			return jsonResponse.getString("access_token");
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

}
