package test.core;

import core.model.AbstractIntegration;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class MockIntegration extends AbstractIntegration {

	public static final String DEFAULT_USERNAME = "mock-username";

	public static final String DEFAULT_PASSWORD = "mock-password";

	private MockIntegration(FieldValueMap fieldValueMap) {
		super(fieldValueMap.getIdValue(), fieldValueMap);
	}

	public static MockIntegration create(String id) {
		Fields fields = new MockPlatform().getFields();
		return new MockIntegration(FieldValueMap.builder(fields).setField(MockPlatform.ID_FIELD_LABEL, id)
				.setField(MockPlatform.USERNAME_LABEL, DEFAULT_USERNAME)
				.setField(MockPlatform.PASSWORD_LABEL, DEFAULT_PASSWORD).create());
	}

	@Override
	public void post(String message) {
	}

}
