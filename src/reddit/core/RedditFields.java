package reddit.core;

import core.schema.Field;
import core.schema.Fields;

class RedditFields {

	public static final Field USERNAME = Field.builder("Username").id().create();

	private RedditFields() {
	}

	public static Fields getReceiverFields() {
		return Fields.create(USERNAME);
	}

}
