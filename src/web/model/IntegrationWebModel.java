package web.model;

import java.util.Map;
import java.util.Optional;

import web.schema.FieldWebModel;

public class IntegrationWebModel {

	private PlatformWebModel platform;

	private String id;

	private String label;

	private Map<FieldWebModel, String> valueMap;

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

	public Map<FieldWebModel, String> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<FieldWebModel, String> valueMap) {
		this.valueMap = valueMap;
	}

}
