package slack.core;

import core.schema.Field;
import core.schema.Fields;

class SlackFields {

	public static final Field ACCESS_TOKEN = Field.builder("Access token").create();

	public static final Field NAME = Field.builder("Name").id().create();

	public static final Field TEAM_NAME = Field.builder("Team name").id().create();

	private SlackFields() {
	}

	public static Fields getUserFields() {
		return Fields.create(TEAM_NAME, ACCESS_TOKEN);
	}

	public static Fields getReceiverFields() {
		return Fields.create(NAME);
	}

}
