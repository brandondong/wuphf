package slack.core;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import core.model.OAuthAppDetails;

class SlackTokenRetriever {

	public CompletableFuture<String> getAccessToken(String code) {
		return CompletableFuture.supplyAsync(() -> {
			OAuthAppDetails details = SlackPlatform.APP_DETAILS;
			String tokenUrl = String.format("https://slack.com/api/oauth.access?client_id=%s&client_secret=%s&code=%s",
					details.getClientId(), details.getClientSecret(), code);

			HttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(tokenUrl);

			try {
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					String content = EntityUtils.toString(entity);
					return parseResponse(content);
				}
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
			throw new IllegalStateException("Failed to retrieve access token.");
		});
	}

	private String parseResponse(String content) throws JSONException {
		JSONObject jsonResponse = new JSONObject(content);
		new SlackResponseVerifier().checkNoErrors(jsonResponse);
		return jsonResponse.getString("access_token");
	}

}
