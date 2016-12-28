package facebook.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class FacebookIntegration implements Integration {

	private final String username;

	private final String password;

	public FacebookIntegration(FieldValueMap fieldValueMap) {
		username = fieldValueMap.getValueForField(FacebookFields.EMAIL);
		password = fieldValueMap.getValueForField(FacebookFields.PASSWORD);
	}

	@Override
	public CompletableFuture<Optional<String>> message(String subject, String message, FieldValueMap receiver) {
		return CompletableFuture.completedFuture(Optional.of(("Username: " + username + " Password: " + password)));
	}

}
