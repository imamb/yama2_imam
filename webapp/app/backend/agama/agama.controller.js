'use strict';

angular.module('yamaApp').controller('AgamaCtrl', function ($scope, $modal, $location, Agama, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Agama.getList($scope.searchParams).then(function(agamas) {
			$scope.agamas = agamas;
			//$scope.page = categorys.meta.number + 1;
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(agama) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'agama.form.html',
			controller: 'AgamaFormCtrl',
			size: 'lg',
			resolve: {
				agama: function() {
					return agama;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.agama;
			$scope.search();
		});
	};

	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(agama) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus data Agama Ini?').result.then(function() {
			agama.remove().then(function() {
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

}).controller('AgamaFormCtrl', function($scope, $modalInstance, $validation, Agama, agama) {
	if (agama) {
		$scope.agama = agama;
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
	$scope.submit = function(agama, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (agama.id) {
				agama.put().then(success, error);
			} else {
				Agama.post(agama).then(success, error);
			}
		});
	};
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
}]);
