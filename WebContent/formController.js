angular.module("wuphf", []).controller("formCtrl", function($scope) {	
	$scope.integration = storageService.getFormIntegration();
	$scope.integrations = storageService.getIntegrations();
	$scope.allowableId = integrationId($scope.integration);
	
	$scope.save = function() {
		storageService.saveIntegration($scope.integration);
		location.href = "integrations.html";
	};
	
	$scope.match = function() {
		for (var i = 0; i < $scope.integrations.length; i++) {
			var existing = $scope.integrations[i];
			if (integrationsEqual($scope.integration, existing) && integrationId(existing) != $scope.allowableId) {
				return true;
			}
		}
		return false;
	}
});