'use strict'

angular.module('yamaApp').factory('News',function(Restangular){
	return Restangular.service('news');
});