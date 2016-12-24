package web.core;

import core.model.IPlatformManager;
import core.model.Integration;
import core.model.Platform;
import core.schema.FieldValueMap;
import web.model.IntegrationWebModel;

public class IntegrationConverter {

	private final IntegrationWebModel webModel;

	private final IPlatformManager manager;

	private IntegrationConverter(IntegrationWebModel webModel, IPlatformManager manager) {
		this.webModel = webModel;
		this.manager = manager;
	}

	public static IntegrationConverter from(IntegrationWebModel integration, IPlatformManager manager) {
		return new IntegrationConverter(integration, manager);
	}

	public Integration convert() {
		Platform platform = manager.getPlatformByLabel(webModel.getPlatformLabel());
		FieldValueMap map = new FieldValueMapConverter(platform.getUserFields()).createMap(webModel.getValueMap());
		return platform.createIntegration(map);
	}

}
