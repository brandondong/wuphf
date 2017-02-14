package slack.core;

import org.json.JSONException;
import org.json.JSONObject;

class SlackResponseVerifier {

	/**
	 * Throws a runtime exception if response contains errors
	 */
	public JSONObject parseResponse(String response) throws JSONException {
		JSONObject jsonResponse = new JSONObject(response);
		if (!jsonResponse.getBoolean("ok")) {
			throw new IllegalStateException(
					String.format("Slack responded with the following errors: %s.", jsonResponse.getString("error")));
		}
		return jsonResponse;
	}

}
