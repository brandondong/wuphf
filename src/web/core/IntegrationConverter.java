package web.core;

import java.util.Map;

import core.model.Integration;
import core.model.Platform;
import core.model.PlatformManager;
import core.schema.FieldValueMap;
import core.schema.Fields;
import web.model.IntegrationWebModel;
import web.schema.FieldWebModel;

public class IntegrationConverter {

	private final IntegrationWebModel integration;

	private IntegrationConverter(IntegrationWebModel integration) {
		this.integration = integration;
	}

	public static IntegrationConverter from(IntegrationWebModel integration) {
		return new IntegrationConverter(integration);
	}

	public Integration create() {
		Platform platform = PlatformManager.instance().getPlatformByLabel(integration.getPlatform().getLabel());
		FieldValueMap map = createFieldValueMap(platform.getFields(), integration.getValueMap());
		return platform.createIntegration(integration.getIntegrationLabel(), map);
	}

	private FieldValueMap createFieldValueMap(Fields fields, Map<FieldWebModel, String> valueMap) {
		FieldValueMap.Builder builder = FieldValueMap.builder(fields);
		valueMap.keySet().forEach(f -> builder.setField(f.getLabel(), valueMap.get(f)));
		return builder.create();
	}

}
