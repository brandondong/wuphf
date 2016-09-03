const CURRENT_FORM_INTEGRATION_KEY = "current.form.integration.key";

var storageService = {
		setFormIntegration: function(integration) {
			localStorage.setItem(CURRENT_FORM_INTEGRATION_KEY, JSON.stringify(integration));
		},
		
		getFormIntegration: function() {
			return JSON.parse(localStorage.getItem(CURRENT_FORM_INTEGRATION_KEY));
		}
};