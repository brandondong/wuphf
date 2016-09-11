angular.module("wuphf", []).controller("formCtrl", function($scope) {	
	$scope.integration = storageService.getFormIntegration();
	$scope.integrations = storageService.getIntegrations();
	
	$scope.save = function() {
		storageService.saveIntegration($scope.integration);
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
});