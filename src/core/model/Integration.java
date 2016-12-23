package core.model;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import core.schema.FieldValueMap;

public interface Integration {

	/**
	 * Sends a message to the specified receiving integration
	 * 
	 * @param message
	 *            the message to send
	 * @param receiver
	 *            the receiving account of the message
	 * @return a promised optional message when the post completes
	 */
	CompletableFuture<Optional<String>> message(String message, FieldValueMap receiver);

}
