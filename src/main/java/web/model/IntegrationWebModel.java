package web.model;

import java.util.HashMap;
import java.util.Map;

import core.model.Platform;
import core.schema.FieldValueMap;

public class IntegrationWebModel {

	private String platformLabel;

	private Map<String, String> valueMap;

	private String idField;

	/**
	 * Public constructor for JAXB, use {
	 * {@link #createFrom(FieldValueMap, Platform)} to instantiate instead
	 */
	public IntegrationWebModel() {
	}

	private IntegrationWebModel(String platformLabel, Map<String, String> valueMap, String idField) {
		this.platformLabel = platformLabel;
		this.valueMap = valueMap;
		this.idField = idField;
	}

	public static IntegrationWebModel createFrom(FieldValueMap integration, Platform platform) {
		Map<String, String> valueMap = new HashMap<>();
		platform.getUserFields().stream().forEach((f) -> valueMap.put(f.getLabel(), integration.getValueForField(f)));
		return new IntegrationWebModel(platform.getLabel(), valueMap, integration.getIdValue());
	}

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

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

}
