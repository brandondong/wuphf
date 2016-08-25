package web.model;

import core.model.Integration;

public class IntegrationIdentifier {

	private String integrationId;

	private String platformLabel;

	public boolean identifies(Integration integration) {
		return integration.getId().equals(integrationId) && integration.getPlatform().getLabel().equals(platformLabel);
	}

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	public String getPlatformLabel() {
		return platformLabel;
	}

	public void setPlatformLabel(String platformLabel) {
		this.platformLabel = platformLabel;
	}

}
