package core.model;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Integration {

	/**
	 * Posts a status message
	 * 
	 * @param message
	 *            the status to post
	 * @return a promised optional message when the post completes
	 */
	CompletableFuture<Optional<String>> post(String message);

}
