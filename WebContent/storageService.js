const CURRENT_FORM_INTEGRATION_KEY = "current.form.integration.key";

const INTEGRATIONS_KEY = "integrations.key";

var storageService = {
		setFormIntegration: function(integration) {
			localStorage.setItem(CURRENT_FORM_INTEGRATION_KEY, JSON.stringify(integration));
		},
		
		getFormIntegration: function() {
			return JSON.parse(localStorage.getItem(CURRENT_FORM_INTEGRATION_KEY));
		},
		
		saveIntegration: function(integration) {
			var integrations = storageService.getIntegrations();
			
			function addOrModify(integration, integrations) {
				for (var i = 0; i < integrations.length; i++) {
					var existing = integrations[i];
					if (integrationsEqual(integration, existing)) {
						integrations[i] = integration;
						return;
					}
				}
				integrations.push(integration);
			}
			
			addOrModify(integration, integrations);
			localStorage.setItem(INTEGRATIONS_KEY, JSON.stringify(integrations));
		},
		
		getIntegrations: function() {
			return JSON.parse(localStorage.getItem(INTEGRATIONS_KEY)) || [];
		},
		
		deleteSelected: function(integration) {
			var integrations = storageService.getIntegrations();
			for (var i = 0; i < integrations.length; i++) {
				var existing = integrations[i];
				if (integrationsEqual(integration, existing)) {
					integrations.splice(i, 1);
					localStorage.setItem(INTEGRATIONS_KEY, JSON.stringify(integrations));
					return;
				}
			}
		}
};

var Integration = function(platform) {
	this.platform = platform;
	this.valueMap = {};
	for (var i = 0; i < platform.fields.length; i++) {
		var field = platform.fields[i];
		if (field.idField) {
			this.idField = field.label;
			return;
		}
	}
}

function integrationsEqual(i1, i2) {
	return integrationId(i1) == integrationId(i2) && i1.platform.label == i2.platform.label;
}

function integrationId(i) {
	return i.valueMap[i.idField];
}