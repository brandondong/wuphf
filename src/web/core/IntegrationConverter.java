package web.core;

import java.util.Map;

import core.model.Integration;
import core.model.Platform;
import core.model.PlatformManager;
import core.schema.FieldValueMap;
import core.schema.Fields;
import web.model.IntegrationWebModel;

public class IntegrationConverter {

	private final IntegrationWebModel integration;

	private IntegrationConverter(IntegrationWebModel integration) {
		this.integration = integration;
	}

	public static IntegrationConverter from(IntegrationWebModel integration) {
		return new IntegrationConverter(integration);
	}

	public Integration convert() {
		Platform platform = PlatformManager.instance().getPlatformByLabel(integration.getPlatform().getLabel());
		FieldValueMap map = createFieldValueMap(platform.getFields(), integration.getValueMap());
		return platform.createIntegration(getIntegrationLabel(map), map);
	}

	private String getIntegrationLabel(FieldValueMap map) {
		return integration.getIntegrationLabel().orElse(map.getIdValue());
	}

	private FieldValueMap createFieldValueMap(Fields fields, Map<String, String> valueMap) {
		FieldValueMap.Builder builder = FieldValueMap.builder(fields);
		valueMap.keySet().forEach(f -> builder.setField(f, valueMap.get(f)));
		return builder.create();
	}

}
