'use strict'

angular.module('yamaApp').factory('Produk',function(Restangular){
	return Restangular.service('produk');
});