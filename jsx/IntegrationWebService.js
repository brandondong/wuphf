const PLATFORM_URL = "rest/integration/platforms";

const CREATE_URL = "rest/integration/create";

export default class IntegrationWebService {
	
	getPlatforms() {
		return new Promise((resolve, reject) => {
			let xmlhttp = this.createRequest(resolve, reject);
			xmlhttp.open("GET", PLATFORM_URL, true);
			xmlhttp.send();
		});
	}
	
	createIntegration(platformLabel, properties) {
		return new Promise((resolve, reject) => {
			let xmlhttp = this.createRequest(resolve, reject);
			xmlhttp.open("POST", CREATE_URL, true);
			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.send(JSON.stringify({platformLabel: platformLabel, properties: properties}));
		});
	}
	
	createRequest(resolve, reject) {
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					resolve(JSON.parse(xmlhttp.responseText));
				} else {
					reject(xmlhttp.responseText);
				}
			}
		}
		return xmlhttp;
	}
}