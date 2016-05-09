'use strict';

angular.module('yamaApp').config(function ($stateProvider) {
	$stateProvider.state('backend.news', {
		url: '/news',
		templateUrl: 'backend/news/news.html',
		controller: 'NewsCtrl'
	});
});
