const NUM_COLUMNS = 10;

angular.module("wuphf", []).controller("platformCtrl", function($scope, $http) {
	$scope.split = [];
	
	integrationService.platforms($http, function(platforms) {
		for (var i = 0; i < platforms.length; i += NUM_COLUMNS) {
			$scope.split.push(platforms.slice(i, i + NUM_COLUMNS));
		}
	});
	
	$scope.select = function(platform) {
		$scope.selected = platform;
	};
});