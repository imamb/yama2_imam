'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.produk', {
		url: '/produk',
		templateUrl: 'backend/produk/produk.html',
		controller: 'ProdukCtrl'
	});
});
