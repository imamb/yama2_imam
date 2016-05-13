'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.penjualan', {
		url: '/penjualan',
		templateUrl: 'backend/penjualan/penjualan.html',
		controller: 'PenjualanCtrl'
	});
});
