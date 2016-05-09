'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.siswa', {
		url: '/siswa',
		templateUrl: 'backend/siswa/siswa.html',
		controller: 'SiswaCtrl'
	});
});
