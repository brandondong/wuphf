package slack.core;

import org.json.JSONException;
import org.json.JSONObject;

class SlackResponseVerifier {

	/**
	 * Throws a runtime exception if response contains errors
	 */
	public void checkNoErrors(JSONObject response) throws JSONException {
		if (!response.getBoolean("ok")) {
			throw new IllegalStateException(
					String.format("Slack responded with the following errors: %s.", response.getString("error")));
		}
	}

}
