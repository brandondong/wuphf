package test.core;

import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import core.model.AbstractIntegration;
import core.model.Platform;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class MockIntegration extends AbstractIntegration {

	public static final String DEFAULT_USERNAME = "mock-username";

	public static final String DEFAULT_PASSWORD = "mock-password";

	private MockIntegration(Platform platform, FieldValueMap fieldValueMap) {
		super(Optional.empty(), platform, fieldValueMap);
	}

	public static MockIntegration create(String id) {
		Platform platform = new MockPlatform();
		Fields fields = platform.getFields();
		return new MockIntegration(platform,
				FieldValueMap.createWith(fields, ImmutableMap.of(fields.getIdField().getLabel(), id,
						MockPlatform.USERNAME_LABEL, DEFAULT_USERNAME, MockPlatform.PASSWORD_LABEL, DEFAULT_PASSWORD)));
	}

	@Override
	public void post(String message) {
	}

}
