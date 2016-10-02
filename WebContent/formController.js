angular.module("wuphf", []).controller("formCtrl", function($scope) {	
	$scope.integration = storageService.getFormIntegration();
	$scope.integrations = getExistingIntegrations();
	
	$scope.save = function() {
		// Make integrations empty to avoid identical id error message
		var temp = $scope.integrations;
		$scope.integrations = [];
		temp.push($scope.integration);
		storageService.saveIntegrations(temp);
		location.href = "integrations.html";
	};
	
	$scope.match = function() {
		for (var i = 0; i < $scope.integrations.length; i++) {
			var existing = $scope.integrations[i];
			if (integrationsEqual($scope.integration, existing)) {
				return true;
			}
		}
		return false;
	}
	
	function getExistingIntegrations() {
		var integrations = storageService.getIntegrations();
		for (var i = 0; i < integrations.length; i++) {
			if (integrationsEqual($scope.integration, integrations[i])) {
				integrations.splice(i, 1);
				return integrations;
			}
		}
		return integrations;
	}
});