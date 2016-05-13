'use strict'

angular.module('yamaApp').factory('Pembelian',function(Restangular){
	return Restangular.service('pembelian');
});