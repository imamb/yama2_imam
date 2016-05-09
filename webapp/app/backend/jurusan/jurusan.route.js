'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.jurusan', {
		url: '/jurusan',
		templateUrl: 'backend/jurusan/jurusan.html',
		controller: 'JurusanCtrl'
	});
});
