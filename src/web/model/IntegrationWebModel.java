package web.model;

import java.util.Map;

public class IntegrationWebModel {

	private String platformLabel;

	private Map<String, String> valueMap;

	public String getPlatformLabel() {
		return platformLabel;
	}

	public void setPlatformLabel(String platformLabel) {
		this.platformLabel = platformLabel;
	}

	public Map<String, String> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, String> valueMap) {
		this.valueMap = valueMap;
	}

}
