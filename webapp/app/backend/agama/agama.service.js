'use strict'

angular.module('yamaApp').factory('Agama',function(Restangular){
	return Restangular.service('agama');
});