package reddit.core;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

import org.json.JSONException;
import org.json.JSONObject;

import core.model.Platform;

public class RedditTokenRetriever {

	private static final String CODE = "code";

	private static final String ERROR = "error";

	private static final String TOKEN_URL = "https://www.reddit.com/api/v1/access_token";

	public CompletableFuture<RedditToken> getToken(Map<String, String> parameters) {
		return CompletableFuture.supplyAsync(() -> {
			if (parameters.containsKey(ERROR)) {
				throw new IllegalStateException(
						String.format("Failed to grant permissions: %s.", parameters.get(ERROR)));
			}
			String code = parameters.get(CODE);

			try {
				byte[] out = transformPostData(getPostParameters(code));
				JSONObject json = sendPostRequest(out);
				String access = json.getString("access_token");
				String refresh = json.getString("refresh_token");
				long expiresIn = json.getLong("expires_in");
				return RedditToken.expiresIn(access, refresh, expiresIn);
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
		});
	}

	public CompletableFuture<RedditToken> refreshToken(RedditToken token) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				byte[] out = transformPostData(getRefreshPostParameters(token.getRefreshToken()));
				JSONObject json = sendPostRequest(out);
				String access = json.getString("access_token");
				long expiresIn = json.getLong("expires_in");
				return RedditToken.expiresIn(access, token.getRefreshToken(), expiresIn);
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
		});
	}

	private JSONObject sendPostRequest(byte[] out)
			throws MalformedURLException, IOException, ProtocolException, JSONException {
		URL url = new URL(TOKEN_URL);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);

		addHeaders(http);
		http.setFixedLengthStreamingMode(out.length);
		http.connect();
		try (OutputStream os = http.getOutputStream()) {
			os.write(out);
		}
		String result = new BufferedReader(new InputStreamReader(http.getInputStream())).lines().collect(joining("\n"));
		return new JSONObject(result);
	}

	private void addHeaders(HttpURLConnection http) {
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		http.setRequestProperty("Authorization", getAuthorizationHeader());
		http.setRequestProperty("User-agent", "wuphf");
	}

	private byte[] transformPostData(Map<String, String> parameters) throws UnsupportedEncodingException {
		StringJoiner sj = new StringJoiner("&");
		for (Map.Entry<String, String> entry : parameters.entrySet())
			sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
		byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
		return out;
	}

	private Map<String, String> getPostParameters(String code) {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "authorization_code");
		params.put(CODE, code);
		params.put("redirect_uri", Platform.APP_REDIRECT);
		return params;
	}

	private Map<String, String> getRefreshPostParameters(String token) {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "refresh_token");
		params.put("refresh_token", token);
		return params;
	}

	private String getAuthorizationHeader() {
		IRedditAppDetails details = IRedditAppDetails.getDefault();
		String encoding = Base64.getEncoder()
				.encodeToString(String.format("%s:%s", details.getClientId(), details.getClientSecret()).getBytes());
		return String.format("Basic %s", encoding);
	}

}
