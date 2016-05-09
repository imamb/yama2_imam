'use strict';

angular.module('yamaApp').controller('KelasCtrl', function ($scope, $modal, $location, Kelas, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Kelas.getList($scope.searchParams).then(function(kelass) {
			$scope.kelass = kelass;
			//$scope.page = categorys.meta.number + 1;
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(kelas) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'kelas.form.html',
			controller: 'KelasFormCtrl',
			size: 'lg',
			resolve: {
				kelas: function() {
					return kelas;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.kelas;
			$scope.search();
		});
	};

	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(kelas) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus data Kelas Ini?').result.then(function() {
			kelas.remove().then(function() {
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

}).controller('KelasFormCtrl', function($scope, $modalInstance, $validation, Kelas, kelas) {
	if (kelas) {
		$scope.kelas = kelas;
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
	$scope.submit = function(kelas, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (kelas.id) {
				kelas.put().then(success, error);
			} else {
				Kelas.post(kelas).then(success, error);
			}
		});
	};
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
}]);
