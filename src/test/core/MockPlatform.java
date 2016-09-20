package test.core;

import core.model.AbstractPlatform;
import core.model.Integration;
import core.schema.Field;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class MockPlatform extends AbstractPlatform {

	public static final String LABEL = "mock-platform-label";

	public static final String ID_FIELD_LABEL = "mock-id-field-label";

	public static final String USERNAME_LABEL = "mock-username-label";

	public static final String PASSWORD_LABEL = "mock-password-label";

	public static final Field USERNAME_FIELD = Field.builder(USERNAME_LABEL).description("a mock username field")
			.create();

	public static final Field PASSWORD_FIELD = Field.builder(PASSWORD_LABEL).description("a mock password field")
			.create();

	public static final String LOGO_LINK = "not a valid image.png";

	@Override
	public String getLabel() {
		return LABEL;
	}

	@Override
	public Fields getFields() {
		Field idField = Field.builder(ID_FIELD_LABEL).id().create();
		return Fields.create(idField, USERNAME_FIELD, PASSWORD_FIELD);
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new MockIntegration(fieldValueMap);
	}

	@Override
	public String getLogoImageLink() {
		return LOGO_LINK;
	}

}
