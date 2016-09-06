angular.module("wuphf", []).controller("integrationCtrl", function($scope) {	
	$scope.integrations = storageService.getIntegrations();
});