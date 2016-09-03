angular.module("wuphf", []).controller("formCtrl", function($scope) {	
	$scope.integration = storageService.getFormIntegration();
});