package facebook.core;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import core.model.OAuthToken;
import web.service.HttpService;

class FacebookOAuthService {

	private final String accessToken;

	public FacebookOAuthService(OAuthToken token) {
		accessToken = token.getAccessToken();
	}

	public CompletableFuture<String> getUserFullname() {
		String url = String.format("https://graph.facebook.com/v2.8/me?access_token=%s", accessToken);
		HttpGet get = new HttpGet(url);
		return new HttpService().getResponse(get).thenApply((s) -> {
			try {
				return new FacebookResponseParser().parse(s).getString("name");
			} catch (JSONException e) {
				throw new IllegalStateException(e);
			}
		});
	}

	public CompletableFuture<Void> sendMessage(String subject, String message, String receiverEmail) {
		String url = String.format("https://graph.facebook.com/v2.8/me/messages?access_token=%s", accessToken);
		HttpPost post = new HttpPost(url);
		try {
			addMessageParams(receiverEmail, subject, message, post);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
		return new HttpService().getResponse(post).thenApply(s -> null);
	}

	private void addMessageParams(String username, String subject, String message, HttpPost httpPost)
			throws UnsupportedEncodingException {
		List<BasicNameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("text", message));
		params.add(new BasicNameValuePair("to", username));
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	}

}
