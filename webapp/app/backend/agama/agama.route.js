'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.agama', {
		url: '/agama',
		templateUrl: 'backend/agama/agama.html',
		controller: 'AgamaCtrl'
	});
});
