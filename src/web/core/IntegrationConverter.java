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

	private final PlatformManager manager;

	private IntegrationConverter(IntegrationWebModel webModel, PlatformManager manager) {
		this.webModel = webModel;
		this.manager = manager;
	}

	public static IntegrationConverter from(IntegrationWebModel integration, PlatformManager manager) {
		return new IntegrationConverter(integration, manager);
	}

	public Integration convert() {
		Platform platform = manager.getPlatformByLabel(webModel.getPlatformLabel());
		FieldValueMap map = createFieldValueMap(platform.getFields(), webModel.getValueMap());
		return platform.createIntegration(map);
	}

	private FieldValueMap createFieldValueMap(Fields fields, Map<String, String> valueMap) {
		FieldValueMap.Builder builder = FieldValueMap.builder(fields);
		valueMap.keySet().forEach(f -> builder.setField(f, valueMap.get(f)));
		return builder.create();
	}

}
