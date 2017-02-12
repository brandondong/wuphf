package slack.core;

import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import web.service.HttpService;

class SlackOAuthService {

	private final String accessToken;

	public SlackOAuthService(String accessToken) {
		this.accessToken = accessToken;
	}

	public CompletableFuture<String> getTeamName() {
		String teamUrl = String.format("https://slack.com/api/team.info?token=%s", accessToken);
		HttpGet httpGet = new HttpGet(teamUrl);
		return new HttpService().getResponse(httpGet).thenApply(this::parseTeamResponse);
	}

	private String parseTeamResponse(String content) {
		try {
			JSONObject response = new JSONObject(content);
			new SlackResponseVerifier().checkNoErrors(response);
			return response.getJSONObject("team").getString("name");
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

}
