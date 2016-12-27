package reddit.core;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
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

	public CompletableFuture<String> getToken(Map<String, String> parameters) {
		return CompletableFuture.supplyAsync(() -> {
			if (parameters.containsKey(ERROR)) {
				throw new IllegalStateException(String.format("Failed to login: %s.", parameters.get(ERROR)));
			}
			String code = parameters.get(CODE);

			try {
				URL url = new URL(TOKEN_URL);
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.setRequestMethod("POST");
				http.setDoOutput(true);

				addHeaders(http);

				byte[] out = getPostData(code);
				http.setFixedLengthStreamingMode(out.length);
				http.connect();
				try (OutputStream os = http.getOutputStream()) {
					os.write(out);
				}

				String result = new BufferedReader(new InputStreamReader(http.getInputStream())).lines()
						.collect(joining("\n"));
				return new JSONObject(result).getString("access_token");
			} catch (IOException | JSONException e) {
				throw new IllegalStateException(e);
			}
		});

	}

	private void addHeaders(HttpURLConnection http) {
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		http.setRequestProperty("Authorization", getAuthorizationHeader());
		http.setRequestProperty("User-agent", "wuphf");
	}

	private byte[] getPostData(String code) throws UnsupportedEncodingException {
		StringJoiner sj = new StringJoiner("&");
		for (Map.Entry<String, String> entry : getPostParameters(code).entrySet())
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

	private String getAuthorizationHeader() {
		String encoding = Base64.getEncoder().encodeToString(
				String.format("%s:%s", RedditPlatform.CLIENT_ID, RedditPlatform.CLIENT_SECRET).getBytes());
		return String.format("Basic %s", encoding);
	}

}
