package core.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.schema.FieldValueMap;

class MockIntegration implements Integration {

	public MockIntegration(FieldValueMap map) {
		new MockPlatform().getUserFields().stream().forEach(f -> {
			checkNotNull(map.getValueForField(f));
		});
	}

	@Override
	public CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver) {
		new MockPlatform().getReceiverFields().stream().forEach(f -> {
			checkNotNull(receiver.getValueForField(f));
		});
		return CompletableFuture.completedFuture(Optional.empty());
	}

}
