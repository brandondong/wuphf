const PLATFORMS = "/wuphf/rest/integration/platforms";

var integrationService = {
		platforms: function($http, handler) {
			$http.get(PLATFORMS).then(function(response) {
				handler(response.data);
			});
		}
}