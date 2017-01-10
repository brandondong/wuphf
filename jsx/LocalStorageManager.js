const INTEGRATIONS_KEY = "integrations.key";

const CURRENT_PLATFORM_KEY = "current.platform.key";

export default class LocalStorageManager {
	
	getIntegrations() {
		let integrations = localStorage.getItem(INTEGRATIONS_KEY);
		if (integrations == null) {
			return {};
		}
		return JSON.parse(integrations);
	}
	
	saveIntegration(i) {
		let integrations = this.getIntegrations();
		integrations[i.platformLabel] = i;
		localStorage.setItem(INTEGRATIONS_KEY, JSON.stringify(integrations));
	}
	
	saveCurrentPlatform(platformLabel) {
		localStorage.setItem(CURRENT_PLATFORM_KEY, platformLabel);
	}
	
	getCurrentPlatform() {
		return localStorage.getItem(CURRENT_PLATFORM_KEY);
	}
}