package core.model;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.schema.FieldValueMap;

public interface Integration {

	/**
	 * Sends a message to the specified receiving integration
	 * 
	 * @param subject
	 *            the subject of the message
	 * @param message
	 *            the message to send
	 * @param receiver
	 *            the receiving account of the message
	 * @return a promised optional {@link FieldValueMap} when the post completes
	 *         if the integration changed
	 */
	CompletableFuture<Optional<FieldValueMap>> message(String subject, String message, FieldValueMap receiver);

}
