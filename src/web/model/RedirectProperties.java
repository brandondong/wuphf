package web.model;

import java.util.Map;

public class RedirectProperties {

	private String platformLabel;

	private Map<String, String> properties;

	public String getPlatformLabel() {
		return platformLabel;
	}

	public void setPlatformLabel(String platformLabel) {
		this.platformLabel = platformLabel;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
