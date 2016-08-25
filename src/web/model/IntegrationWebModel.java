package web.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import core.model.Integration;
import core.schema.Field;
import core.schema.FieldValueMap;
import web.schema.FieldWebModel;

public class IntegrationWebModel {

	private PlatformWebModel platform;

	private String id;

	private String label;

	private Map<FieldWebModel, String> valueMap;

	/**
	 * Public constructor for JAXB, use {{@link #createFrom(Integration)} to
	 * instantiate instead
	 */
	public IntegrationWebModel() {
	}

	private IntegrationWebModel(PlatformWebModel platform, String id, String label,
			Map<FieldWebModel, String> valueMap) {
		this.platform = platform;
		this.id = id;
		this.label = label;
		this.valueMap = valueMap;
	}

	public static IntegrationWebModel createFrom(Integration integration) {
		return new IntegrationWebModel(PlatformWebModel.createFrom(integration.getPlatform()), integration.getId(),
				integration.getLabel().orElse(null), createValueMap(integration.getFieldValueMap()));
	}

	private static Map<FieldWebModel, String> createValueMap(FieldValueMap fieldValueMap) {
		Map<FieldWebModel, String> map = new HashMap<>();
		for (Field field : fieldValueMap.getFields()) {
			map.put(FieldWebModel.createFrom(field), fieldValueMap.getValueForField(field));
		}
		return map;
	}

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

}
