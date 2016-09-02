angular.module("wuphf", []).controller("platformCtrl", function($scope, $http) {	
	integrationService.platforms($http, function(platforms) {
		$scope.platforms = platforms;
	});
	
	$scope.select = function(platform) {
		$scope.selected = platform;
	};
});