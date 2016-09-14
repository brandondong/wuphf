angular.module("wuphf", []).controller("integrationCtrl", function($scope) {	
	$scope.integrations = storageService.getIntegrations();
	
	$scope.getLabel = function(integration) {
		var label = integration.label;
		if (label != null && label.length > 0) {
			return label;
		}
		return integration.valueMap[integration.idField];
	};
	
	$scope.select = function(integration) {
		$scope.selected = integration;
	}
});