package slack.core;

import core.schema.Field;
import core.schema.Fields;

class SlackFields {

	public static final Field ACCESS_TOKEN = Field.builder("Access token").create();

	public static final Field NAME = Field.builder("Name").id().create();

	private SlackFields() {
	}

	public static Fields getUserFields() {
		return Fields.create(NAME, ACCESS_TOKEN);
	}

	public static Fields getReceiverFields() {
		return Fields.create(NAME);
	}

}
