'use strict'

angular.module('yamaApp').factory('Siswa',function(Restangular){
	return Restangular.service('siswa');
});