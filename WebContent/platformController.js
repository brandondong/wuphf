angular.module("wuphf", []).controller("platformCtrl", function($scope, $http) {	
	integrationService.platforms($http, function(platforms) {
		$scope.platforms = platforms;
	});
	
	$scope.select = function(platform) {
		$scope.selected = platform;
	};
	
	$scope.create = function() {
		storageService.setFormIntegration(new Integration($scope.selected));
		location.href = "form.html";
	}
});