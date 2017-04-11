package core.model;

import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.Platform;
import core.schema.Field;
import core.schema.FieldValueMap;
import core.schema.Fields;

public class MockPlatform implements Platform {

	public static final String LABEL = "mock-platform-label";

	public static final String REDIRECT_URL = "mock://redirect";

	public static final String ID_FIELD_LABEL = "mock-id-field-label";

	public static final String USERNAME_LABEL = "mock-username-label";

	public static final String PASSWORD_LABEL = "mock-password-label";

	public static final Field ID_FIELD = Field.builder(ID_FIELD_LABEL).id().create();

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
	public Fields getUserFields() {
		return Fields.create(ID_FIELD, USERNAME_FIELD, PASSWORD_FIELD);
	}

	@Override
	public Fields getReceiverFields() {
		return Fields.create(ID_FIELD, USERNAME_FIELD);
	}

	@Override
	public Integration createIntegration(FieldValueMap fieldValueMap) {
		return new MockIntegration(fieldValueMap);
	}

	@Override
	public CompletableFuture<FieldValueMap> createIntegrationFromRedirect(String code) {
		FieldValueMap.Builder map = FieldValueMap.builder(getUserFields()).setField(ID_FIELD, code)
				.setField(USERNAME_FIELD, "user").setField(PASSWORD_FIELD, "pass");
		return CompletableFuture.completedFuture(map.create());
	}

	@Override
	public String getLogoImageLink() {
		return LOGO_LINK;
	}

	@Override
	public String getLoginRedirectUrl() {
		return REDIRECT_URL;
	}

}
