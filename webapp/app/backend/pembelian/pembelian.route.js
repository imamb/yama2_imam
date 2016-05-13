'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.pembelian', {
		url: '/pembelian',
		templateUrl: 'backend/pembelian/pembelian.html',
		controller: 'PembelianCtrl'
	});
});
