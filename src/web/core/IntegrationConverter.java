package web.core;

import core.model.Integration;
import core.model.Platform;
import core.model.PlatformManager;
import core.schema.FieldValueMap;
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
		FieldValueMap map = new FieldValueMapConverter(platform.getUserFields()).createMap(webModel.getValueMap());
		return platform.createIntegration(map);
	}

}
