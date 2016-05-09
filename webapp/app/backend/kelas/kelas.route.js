'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.kelas', {
		url: '/kelas',
		templateUrl: 'backend/kelas/kelas.html',
		controller: 'KelasCtrl'
	});
});
