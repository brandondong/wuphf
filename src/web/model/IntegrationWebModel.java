package web.model;

import java.util.Map;
import java.util.Optional;

public class IntegrationWebModel {

	private PlatformWebModel platform;

	private String id;

	private String label;

	private Map<String, String> valueMap;

	public Optional<String> getIntegrationLabel() {
		return Optional.ofNullable(label);
	}

	public PlatformWebModel getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformWebModel platform) {
		this.platform = platform;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Used by JAXB, use {@link #getIntegrationLabel()} to get label instead
	 */
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, String> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, String> valueMap) {
		this.valueMap = valueMap;
	}

}
