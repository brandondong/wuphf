package web.model;

import java.util.HashMap;
import java.util.Map;

import core.model.Integration;

public class IntegrationWebModel {

	private PlatformWebModel platform;

	private String id;

	private String label;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Integration)} to
	 * instantiate instead
	 */
	public IntegrationWebModel() {
	}

	private IntegrationWebModel(PlatformWebModel platform, String id, String label) {
		this.platform = platform;
		this.id = id;
		this.label = label;
	}

	public static IntegrationWebModel createFrom(Integration integration) {
		return new IntegrationWebModel(PlatformWebModel.createFrom(integration.getPlatform()), integration.getId(),
				integration.getLabel().orElse(null));
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, String> getValueMap() {
		return new HashMap<>();
	}

}
