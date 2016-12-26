package reddit.core;

import core.schema.Field;
import core.schema.Fields;

class RedditFields {

	public static final Field ACCESS_TOKEN = Field.builder("Access token").create();

	public static final Field USERNAME = Field.builder("Username").id().create();

	private RedditFields() {
	}

	public static Fields getUserFields() {
		return Fields.create(USERNAME, ACCESS_TOKEN);
	}

	public static Fields getReceiverFields() {
		return Fields.create(USERNAME);
	}

}
