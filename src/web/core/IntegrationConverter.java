package web.core;

import java.util.Map;

import core.model.Integration;
import core.model.Platform;
import core.model.PlatformManager;
import core.schema.FieldValueMap;
import core.schema.Fields;
import web.model.IntegrationWebModel;

public class IntegrationConverter {

	private final IntegrationWebModel webModel;

	private final FieldValueMap map;

	private final Integration integration;

	private IntegrationConverter(IntegrationWebModel webModel) {
		this.webModel = webModel;
		Platform platform = PlatformManager.instance().getPlatformByLabel(webModel.getPlatform().getLabel());
		map = createFieldValueMap(platform.getFields(), webModel.getValueMap());
		integration = platform.createIntegration(map);
	}

	public static IntegrationConverter from(IntegrationWebModel integration) {
		return new IntegrationConverter(integration);
	}

	public Integration convert() {
		return integration;
	}

	public String getIntegrationLabel() {
		return webModel.getIntegrationLabel().orElse(map.getIdValue());
	}

	private FieldValueMap createFieldValueMap(Fields fields, Map<String, String> valueMap) {
		FieldValueMap.Builder builder = FieldValueMap.builder(fields);
		valueMap.keySet().forEach(f -> builder.setField(f, valueMap.get(f)));
		return builder.create();
	}

}
