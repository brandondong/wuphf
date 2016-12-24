package web.core;

import core.model.IPlatformManager;
import core.model.Platform;
import core.schema.FieldValueMap;
import web.model.IntegrationWebModel;

public class ReceiverConverter {

	private final IntegrationWebModel webModel;

	private final IPlatformManager manager;

	private ReceiverConverter(IntegrationWebModel webModel, IPlatformManager manager) {
		this.webModel = webModel;
		this.manager = manager;
	}

	public static ReceiverConverter from(IntegrationWebModel integration, IPlatformManager manager) {
		return new ReceiverConverter(integration, manager);
	}

	public FieldValueMap convert() {
		Platform platform = manager.getPlatformByLabel(webModel.getPlatformLabel());
		return new FieldValueMapConverter(platform.getReceiverFields()).createMap(webModel.getValueMap());
	}

}
