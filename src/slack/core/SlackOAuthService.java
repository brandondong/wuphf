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

class SlackOAuthService {

	private final String accessToken;

	public SlackOAuthService(String accessToken) {
		this.accessToken = accessToken;
	}

	public CompletableFuture<String> getTeamName() {
		return CompletableFuture.supplyAsync(() -> {
			String teamUrl = String.format("https://slack.com/api/team.info?token=%s", accessToken);
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(teamUrl);

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
		JSONObject response = new JSONObject(content);
		new SlackResponseVerifier().checkNoErrors(response);
		return response.getJSONObject("team").getString("name");
	}

}
