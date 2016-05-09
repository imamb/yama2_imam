'use strict';

angular.module('yamaApp').controller('CategoryCtrl', function ($scope, $modal, $location, Category, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Category.getList($scope.searchParams).then(function(categorys) {
			$scope.categorys = categorys;
			//$scope.page = categorys.meta.number + 1;
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(category) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'category.form.html',
			controller: 'CategoryFormCtrl',
			size: 'lg',
			resolve: {
				category: function() {
					return category;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.kategori;
			$scope.search();
		});
	};

	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(category) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Kategori Ini?').result.then(function() {
			category.remove().then(function() {
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

}).controller('CategoryFormCtrl', function($scope, $modalInstance, $validation, Category, category) {
	if (category) {
		$scope.category = category;
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
	$scope.submit = function(category, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (category.id) {
				category.put().then(success, error);
			} else {
				Category.post(category).then(success, error);
			}
		});
	};
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
}]);
