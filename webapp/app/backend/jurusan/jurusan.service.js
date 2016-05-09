'use strict'

angular.module('yamaApp').factory('Jurusan',function(Restangular){
	return Restangular.service('jurusan');
});