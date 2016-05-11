'use strict';

angular.module('yamaApp').controller('NewsCtrl', function ($scope, $modal, $location, News, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		News.getList($scope.searchParams).then(function(newss) {
			$scope.newss = newss;
			//$scope.page = newss.meta.number + 1;
			angular.forEach(newss,function(news){
				news.getList('categorys').then(function(categorys){
					news.categorys=categorys;
					//console.log('List Kategori'+news.categorys);
				});
			});
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(news) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'news.form.html',
			controller: 'NewsFormCtrl',
			size: 'lg',
			resolve: {
				news: function() {
					return news;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.title;
			$scope.search();
		});
	};

	
	$scope.addCategory = function(news) {
		var modal = $modal.open({
			templateUrl: 'news.category.html',
			controller: 'NewsEditCategoryFormCtrl',
			size: 'lg',
			resolve: {
				news: function() {
					return news;
				}
			}
		});

		modal.result.then(function() {
			$scope.search($scope.searchParams);
		});
	};
	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(news) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Berita dengan Judul '+ news.title +' Ini?').result.then(function() {
			news.remove().then(function() {
				$scope.search();
				alert("Data Berhasil di Hapus");
			});

		});
	};

/*
	$scope.Ganti = function(news) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'news.form.html',
			controller: 'NewsFormCtrl',
			size: 'lg',
			resolve: {
				news: function() {
					return news;
				}
			}
		});
*/

}).controller('NewsFormCtrl', function($scope, $modalInstance, $validation, News, news) {
	if (news) {
		$scope.news = news;
	}

	var success = function(r) {
		$modalInstance.close(r);
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.tutup = function() {
		$modalInstance.close();
	}
	$scope.submit = function(news, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (news.id) {
				news.put().then(success, error);
			} else {
				News.post(news).then(success, error);
			}
		});
	};
}).controller('NewsEditCategoryFormCtrl', function($scope, $modalInstance, $cacheFactory, Category, news) {
	$scope.news = news;
	$scope.categorys = [];

	var invalidateCache = function() {
		$cacheFactory.get('$http').remove(news.one('categorys').getRequestedUrl());
		//console.log('link');
	};

	$scope.loadCategory = function(search) {
		Category.getList({ q: search }).then(function(categorys) {
			$scope.categorys = categorys;
			//console.log('daftar');
		});
		//console.log('ambil kategori');
	};

	$scope.addCategory = function(category) {
		news.one('categorys', category.id).put().then(function() {
			invalidateCache();
			//console.log('tambah');
		});
	};

	$scope.removeCategory = function(category) {
		news.one('categorys', category.id).remove().then(function() {
			invalidateCache();
		});
	};

	$scope.done = $modalInstance.close;
	//console.log('selesai');
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
	}]);
