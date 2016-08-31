const NUM_COLUMNS = 10;

angular.module("wuphf", []).controller("platformCtrl", function($scope, $http) {
	integrationService.platforms($http, function(platforms) {
		$scope.platforms = platforms;
	});
	
	$scope.select = function(platform) {
		$scope.selected = platform;
	};
	
	$scope.split = function() {
		var arrays = [];
		for (var i = 0; i < $scope.platforms.length; i += NUM_COLUMNS) {
			arrays.push($scope.platforms.slice(i, i + NUM_COLUMNS));
		}
		return arrays;
	};
});