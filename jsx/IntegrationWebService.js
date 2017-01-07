const PLATFORM_URL = "rest/integration/platforms";

export default class IntegrationWebService {
	
	getPlatforms() {
		return new Promise(function(resolve, reject) {
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
			xmlhttp.open("GET", PLATFORM_URL, true);
			xmlhttp.send();
		});
	}
}