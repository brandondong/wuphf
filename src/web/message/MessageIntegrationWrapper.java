package web.message;

import web.model.IntegrationWebModel;

/**
 * Contains the message to post and the integrations to post for
 */
public class MessageIntegrationWrapper {

	private IntegrationWebModel integration;

	private String message;

	public IntegrationWebModel getIntegration() {
		return integration;
	}

	public void setIntegration(IntegrationWebModel integration) {
		this.integration = integration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
