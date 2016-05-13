'use strict'

angular.module('yamaApp').factory('Penjualan',function(Restangular){
	return Restangular.service('penjualan');
});