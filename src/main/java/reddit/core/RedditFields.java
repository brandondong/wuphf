package reddit.core;

import core.schema.Field;
import core.schema.Fields;

class RedditFields {

	public static final Field ACCESS_TOKEN = Field.builder("Access token").create();

	public static final Field REFRESH_TOKEN = Field.builder("Refresh token").create();

	public static final Field EXPIRES_AT = Field.builder("Expires at").create();

	public static final Field USERNAME = Field.builder("Username").id().create();

	private RedditFields() {
	}

	public static Fields getUserFields() {
		return Fields.create(USERNAME, ACCESS_TOKEN, REFRESH_TOKEN, EXPIRES_AT);
	}

	public static Fields getReceiverFields() {
		return Fields.create(USERNAME);
	}

}
