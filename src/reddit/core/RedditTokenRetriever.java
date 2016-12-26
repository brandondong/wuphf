package reddit.core;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RedditTokenRetriever {

	private static final String CODE = "code";

	private static final String ERROR = "error";

	public CompletableFuture<String> getToken(Map<String, String> parameters) {
		return CompletableFuture.supplyAsync(() -> {
			if (parameters.containsKey(ERROR)) {
				throw new IllegalArgumentException(String.format("Failed to login: %s", parameters.get(ERROR)));
			}
			String code = parameters.get(CODE);
			return code;
		});

	}
}
