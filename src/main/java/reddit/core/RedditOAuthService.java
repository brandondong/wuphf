package reddit.core;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import web.service.HttpService;

class RedditOAuthService {

	private static final String IDENTITY_INFO = "https://oauth.reddit.com/api/v1/me";

	private static final String PRIVATE_MESSAGE = "https://oauth.reddit.com/api/compose";

	private final String accessToken;

	public RedditOAuthService(RedditToken token) {
		accessToken = token.getAccessToken();
	}

	public CompletableFuture<String> getUsername() {
		HttpGet httpGet = new HttpGet(IDENTITY_INFO);
		initHeaders(httpGet);
		return new HttpService().getResponse(httpGet).thenApply((content) -> {
			try {
				return new JSONObject(content).getString("name");
			} catch (JSONException e) {
				throw new IllegalStateException(e);
			}
		});
	}

	public CompletableFuture<Void> sendMessage(String username, String subject, String message) {
		HttpPost httpPost = new HttpPost(PRIVATE_MESSAGE);
		initHeaders(httpPost);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			addMessageParams(username, subject, message, httpPost);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
		return new HttpService().getResponse(httpPost).thenApply((content) -> {
			checkForErrors(content);
			return null;
		});
	}

	private void checkForErrors(String content) {
		try {
			JSONArray errors = new JSONObject(content).getJSONObject("json").getJSONArray("errors");
			if (errors.length() != 0) {
				throw new IllegalStateException(String.format("The following errors occurred: %s.", errors));
			}
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

	private void initHeaders(AbstractHttpMessage http) {
		http.setHeader("Authorization", String.format("bearer %s", accessToken));
		http.setHeader("User-agent", "wuphf");
	}

	private void addMessageParams(String username, String subject, String message, HttpPost httpPost)
			throws UnsupportedEncodingException {
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("text", message));
		params.add(new BasicNameValuePair("to", username));
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	}

}
