const INTEGRATIONS_KEY = "integrations.key";

export default class LocalStorageManager {
	
	getIntegrations() {
		let integrations = localStorage.getItem(INTEGRATIONS_KEY);
		if (integrations == null) {
			return [];
		}
		return JSON.parse(integrations);
	}
}