package reddit.core;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RedditOAuthService {

	private static final String IDENTITY_INFO = "https://oauth.reddit.com/api/v1/me";

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

	private void initHeaders(AbstractHttpMessage httpGet) {
		httpGet.setHeader("Authorization", String.format("bearer %s", accessToken));
		httpGet.setHeader("User-agent", "wuphf");
	}

}
