'use strict';

angular.module('yamaApp').controller('JurusanCtrl', function ($scope, $modal, $location, Jurusan, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Jurusan.getList($scope.searchParams).then(function(jurusans) {
			$scope.jurusans = jurusans;
			//$scope.page = newss.meta.number + 1;
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(jurusan) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'jurusan.form.html',
			controller: 'JurusanFormCtrl',
			size: 'lg',
			resolve: {
				jurusan: function() {
					return jurusan;
				}
			}
		});
		
		modal.result.then(function(j) {
			$scope.searchParams.q = j.keterangan;
			$scope.search();
		});
	};

	
		// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(jurusan) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Jurusan Ini?').result.then(function() {
			jurusan.remove().then(function() {
				$scope.search();
				alert("Data Berhasil di Hapus");
			});

		});
	};

}).controller('JurusanFormCtrl', function($scope, $modalInstance, $validation, Jurusan, jurusan) {
	if (jurusan) {
		$scope.jurusan = jurusan;
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
	$scope.submit = function(jurusan, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (jurusan.id) {
				jurusan.put().then(success, error);
			} else {
				Jurusan.post(jurusan).then(success, error);
			}
		});
	};
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
	}]);
