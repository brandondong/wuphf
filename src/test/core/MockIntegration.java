package test.core;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class MockIntegration implements Integration {

	public static final String COMPLETION_MESSAGE = "Mock post completed";

	public MockIntegration(FieldValueMap map) {
		new MockPlatform().getUserFields().stream().forEach(f -> {
			checkNotNull(map.getValueForField(f));
		});
	}

	@Override
	public CompletableFuture<Optional<String>> message(String subject, String message, FieldValueMap receiver) {
		new MockPlatform().getReceiverFields().stream().forEach(f -> {
			checkNotNull(receiver.getValueForField(f));
		});
		return CompletableFuture.completedFuture(Optional.of(COMPLETION_MESSAGE));
	}

}
