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
	public CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver) {
		System.out.println(username + password);
		return CompletableFuture.completedFuture(Optional.empty());
	}

}
