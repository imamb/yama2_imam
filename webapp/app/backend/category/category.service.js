'use strict'

angular.module('yamaApp').factory('Category',function(Restangular){
	return Restangular.service('category');
});