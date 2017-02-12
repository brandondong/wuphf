package web.service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Helper class for sending http requests and retrieving their response
 */
public class HttpService {

	public CompletableFuture<String> getResponse(HttpUriRequest http) {
		return CompletableFuture.supplyAsync(() -> {
			HttpClient httpClient = HttpClients.createDefault();
			try {
				HttpResponse response = httpClient.execute(http);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					return EntityUtils.toString(entity);
				}
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
			throw new IllegalStateException("No message entity found in server response.");
		});
	}

}
