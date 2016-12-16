package test.core;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

public class MockIntegration implements Integration {

	public static final String COMPLETION_MESSAGE = "Mock post completed";

	MockIntegration(FieldValueMap fieldValueMap) {
	}

	@Override
	public CompletableFuture<Optional<String>> post(String message) {
		return CompletableFuture.completedFuture(Optional.of(COMPLETION_MESSAGE));
	}

}
