'use strict'

angular.module('yamaApp').factory('Kelas',function(Restangular){
	return Restangular.service('kelas');
});