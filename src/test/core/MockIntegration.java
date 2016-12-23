package test.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class MockIntegration implements Integration {

	public static final String COMPLETION_MESSAGE = "Mock post completed";

	@Override
	public CompletableFuture<Optional<String>> message(String message, FieldValueMap receiver) {
		return CompletableFuture.completedFuture(Optional.of(COMPLETION_MESSAGE));
	}

}
