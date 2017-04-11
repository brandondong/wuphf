package slack.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
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

	public CompletableFuture<String> findUserIdOfUser(String user) {
		String userUrl = String.format("https://slack.com/api/users.list?token=%s", accessToken);
		HttpGet httpGet = new HttpGet(userUrl);
		return new HttpService().getResponse(httpGet).thenApply((content) -> parseIdOfUser(content, user));
	}

	public CompletableFuture<String> findChannelIdOfIMUser(String userId) {
		String listUrl = String.format("https://slack.com/api/im.list?token=%s", accessToken);
		HttpGet httpGet = new HttpGet(listUrl);
		return new HttpService().getResponse(httpGet).thenApply((content) -> parseChannelIdOfIMUser(content, userId));
	}

	public CompletableFuture<Void> sendMessage(String channelId, String text) {
		try {
			String sendUrl = String.format(
					"https://slack.com/api/chat.postMessage?token=%s&channel=%s&text=%s&as_user=true", accessToken,
					channelId, URLEncoder.encode(text, "UTF-8"));
			HttpGet httpGet = new HttpGet(sendUrl);
			return new HttpService().getResponse(httpGet).thenApply((content) -> {
				try {
					new SlackResponseVerifier().parseResponse(content);
					return null;
				} catch (JSONException e) {
					throw new IllegalStateException(e);
				}
			});
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String parseTeamResponse(String content) {
		try {
			JSONObject response = new SlackResponseVerifier().parseResponse(content);
			return response.getJSONObject("team").getString("name");
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

	private String parseIdOfUser(String content, String user) {
		try {
			JSONObject response = new SlackResponseVerifier().parseResponse(content);

			JSONArray members = response.getJSONArray("members");
			for (int i = 0; i < members.length(); i++) {
				JSONObject member = members.getJSONObject(i);
				if (member.getString("name").equals(user) || member.getString("real_name").equals(user)) {
					return member.getString("id");
				}
			}
			throw new IllegalStateException("No user found in team with name " + user);
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

	private String parseChannelIdOfIMUser(String content, String userId) {
		try {
			JSONObject response = new SlackResponseVerifier().parseResponse(content);

			JSONArray ims = response.getJSONArray("ims");
			for (int i = 0; i < ims.length(); i++) {
				JSONObject im = ims.getJSONObject(i);
				if (im.getString("user").equals(userId)) {
					return im.getString("id");
				}
			}
			throw new IllegalStateException("No IM channel found with user ID " + userId);
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

}
