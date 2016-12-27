package reddit.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RedditOAuthService {

	private static final String IDENTITY_INFO = "https://oauth.reddit.com/api/v1/me";

	private static final String PRIVATE_MESSAGE = "https://oauth.reddit.com/api/compose";

	private final String accessToken;

	public RedditOAuthService(String accessToken) {
		this.accessToken = accessToken;
	}

	public CompletableFuture<String> getUsername() {
		return CompletableFuture.supplyAsync(() -> {
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(IDENTITY_INFO);
			initHeaders(httpGet);

			try {
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					String content = EntityUtils.toString(entity);
					return new JSONObject(content).getString("name");
				}
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
			throw new IllegalStateException("Failed to retrieve identity information.");
		});
	}

	public CompletableFuture<Void> sendMessage(String username, String subject, String message) {
		return CompletableFuture.supplyAsync(() -> {
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(PRIVATE_MESSAGE);
			initHeaders(httpPost);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			try {
				addMessageParams(username, subject, message, httpPost);

				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					String content = EntityUtils.toString(entity);
					JSONArray errors = new JSONObject(content).getJSONObject("json").getJSONArray("errors");
					if (errors.length() == 0) {
						return null;
					}
					throw new IllegalStateException(String.format("The following errors occurred: %s.", errors));
				}
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
			throw new IllegalStateException("Failed to send message.");
		});
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
