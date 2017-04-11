package reddit.core;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import core.model.OAuthAppDetails;
import core.model.Platform;
import web.service.HttpService;

class RedditTokenRetriever {

	private static final String TOKEN_URL = "https://www.reddit.com/api/v1/access_token";

	public CompletableFuture<RedditToken> getToken(String code) {
		HttpPost post = createTokenPost();
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("grant_type", "authorization_code"));
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("redirect_uri", Platform.APP_REDIRECT));
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
		return new HttpService().getResponse(post).thenApply(this::parseAccessResponse);
	}

	public CompletableFuture<RedditToken> refreshToken(RedditToken token) {
		HttpPost post = createTokenPost();
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("grant_type", "refresh_token"));
		params.add(new BasicNameValuePair("refresh_token", token.getRefreshToken()));
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
		return new HttpService().getResponse(post).thenApply((content) -> parseRefreshResponse(content, token));
	}

	private HttpPost createTokenPost() {
		HttpPost post = new HttpPost(TOKEN_URL);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.addHeader("Authorization", getAuthorizationHeader());
		post.addHeader("User-agent", "wuphf");
		return post;
	}

	private RedditToken parseAccessResponse(String content) {
		try {
			JSONObject json = parseResponse(content);

			String access = json.getString("access_token");
			String refresh = json.getString("refresh_token");
			long expiresIn = json.getLong("expires_in");
			return RedditToken.expiresIn(access, refresh, expiresIn);
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

	private RedditToken parseRefreshResponse(String content, RedditToken original) {
		try {
			JSONObject json = parseResponse(content);

			String access = json.getString("access_token");
			long expiresIn = json.getLong("expires_in");
			return RedditToken.expiresIn(access, original.getRefreshToken(), expiresIn);
		} catch (JSONException e) {
			throw new IllegalStateException(e);
		}
	}

	private JSONObject parseResponse(String content) throws JSONException {
		JSONObject json = new JSONObject(content);
		if (json.has("error")) {
			throw new IllegalStateException(String.format("Failed to grant permissions: %s.", json.getString("error")));
		}
		return json;
	}

	private String getAuthorizationHeader() {
		OAuthAppDetails details = RedditPlatform.APP_DETAILS;
		String encoding = Base64.getEncoder()
				.encodeToString(String.format("%s:%s", details.getClientId(), details.getClientSecret()).getBytes());
		return String.format("Basic %s", encoding);
	}

}
