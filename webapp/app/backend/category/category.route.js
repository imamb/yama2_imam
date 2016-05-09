'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.category', {
		url: '/category',
		templateUrl: 'backend/category/category.html',
		controller: 'CategoryCtrl'
	});
});
