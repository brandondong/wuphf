package web.message;

import java.util.List;

import core.model.Integration;
import web.model.IntegrationIdentifier;

public class MessageIntegrationWrapper {

	private List<IntegrationIdentifier> integrations;

	private String message;

	public boolean contains(Integration integration) {
		return integrations.stream().anyMatch(i -> i.identifies(integration));
	}

	public List<IntegrationIdentifier> getIntegrations() {
		return integrations;
	}

	public void setIntegrations(List<IntegrationIdentifier> integrations) {
		this.integrations = integrations;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
