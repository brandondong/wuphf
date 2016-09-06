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
					if (integration.id == existing.id && integration.platform.label == existing.platform.label) {
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
		}
};