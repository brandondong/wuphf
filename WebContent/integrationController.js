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
	};
	
	$scope.deleteSelected = function() {
		storageService.deleteSelected($scope.selected);
		$scope.integrations = storageService.getIntegrations();
	};
	
	$scope.edit = function(integration) {
		storageService.setFormIntegration(integration);
		location.href = "form.html";
	};
});