angular.module("wuphf", []).controller("formCtrl", function($scope) {	
	$scope.integration = storageService.getFormIntegration();
	
	$scope.save = function() {
		storageService.saveIntegration($scope.integration);
		location.href = "integrations.html";
	};
});