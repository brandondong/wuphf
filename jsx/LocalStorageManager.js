const INTEGRATIONS_KEY = "integrations.key";

const PEOPLE_KEY = "people.key";

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
	
	saveIntegrations(integrations) {
		localStorage.setItem(INTEGRATIONS_KEY, JSON.stringify(integrations));
	}
	
	getPeople() {
		let people = localStorage.getItem(PEOPLE_KEY);
		if (people == null) {
			return {};
		}
		return JSON.parse(people);
	}
	
	savePeople(people) {
		localStorage.setItem(PEOPLE_KEY, JSON.stringify(people));
	}
	
	saveCurrentPlatform(platformLabel) {
		localStorage.setItem(CURRENT_PLATFORM_KEY, platformLabel);
	}
	
	getCurrentPlatform() {
		return localStorage.getItem(CURRENT_PLATFORM_KEY);
	}
}