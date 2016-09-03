package web.message;

import java.util.List;

import web.model.IntegrationWebModel;

/**
 * Contains the message to post and the integrations to post for
 */
public class MessageIntegrationWrapper {

	private List<IntegrationWebModel> integrations;

	private String message;

	public List<IntegrationWebModel> getIntegrations() {
		return integrations;
	}

	public void setIntegrations(List<IntegrationWebModel> integrations) {
		this.integrations = integrations;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
