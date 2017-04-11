package facebook.core;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.model.Integration;
import core.model.OAuthToken;
import core.schema.FieldValueMap;

class FacebookIntegration implements Integration {

	private final FacebookOAuthService service;

	public FacebookIntegration(FieldValueMap fieldValueMap) {
		OAuthToken accessToken = OAuthToken.expiresAt(fieldValueMap.getValueForField(FacebookFields.ACCESS_TOKEN),
				LocalDateTime.parse(fieldValueMap.getValueForField(FacebookFields.EXPIRES_AT)));
		service = new FacebookOAuthService(accessToken);
	}

	@Override
	public CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver) {
		String receiverEmail = receiver.getValueForField(FacebookFields.EMAIL);
		return service.sendMessage(subject, message, receiverEmail).thenApply(v -> Optional.empty());
	}

}
