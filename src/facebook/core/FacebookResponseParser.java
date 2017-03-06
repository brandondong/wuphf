package facebook.core;

import org.json.JSONException;
import org.json.JSONObject;

class FacebookResponseParser {

	private static final String ERROR_PROP = "error";

	private static final String MESSAGE_PROP = "message";

	public JSONObject parse(String content) throws JSONException {
		JSONObject json = new JSONObject(content);
		if (json.has(ERROR_PROP)) {
			JSONObject error = json.getJSONObject(ERROR_PROP);
			throw new IllegalStateException(error.getString(MESSAGE_PROP));
		}
		return json;
	}

}
