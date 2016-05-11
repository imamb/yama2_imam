'use strict';

angular.module('yamaApp').controller('SiswaCtrl', function ($scope, $modal, $location, Siswa, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Siswa.getList($scope.searchParams).then(function(siswas) {
			$scope.siswas = siswas;
			//$scope.page = categorys.meta.number + 1;
			angular.forEach(siswas,function(siswa){
				siswa.getList('kelass').then(function(kelass){
					siswa.kelas=kelass;
					//console.log('List Kategori'+news.categorys);
				});
				siswa.getList('agamas').then(function(agamas){
					siswa.agama=agamas;
					//console.log('List Kategori'+news.categorys);
				});
			});
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(siswa) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'siswa.form.html',
			controller: 'SiswaFormCtrl',
			size: 'lg',
			resolve: {
				siswa: function() {
					return siswa;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.nis;
			$scope.search();
		});
	};

	$scope.openOrtu = function(siswa) {
		//alert("ok");
		var modal = $modal.open({
			templateUrl: 'siswa.ortu.html',
			controller: 'OrtuFormCtrl',
			size: 'lg',
			resolve: {
				siswa: function() {
					return siswa;
				}
			}
		});
		
		modal.result.then(function(n) {
			$scope.searchParams.q = n.nama;
			$scope.search();
		});
	};
	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(siswa) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus data Siswa Ini?').result.then(function() {
			siswa.remove().then(function() {
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

}).controller('SiswaFormCtrl', function($scope, $modalInstance, $validation, Siswa, siswa,Agama, Kelas) {
	$scope.agamas = [];
	$scope.kelass = [];
	
	$scope.loadAgama = function(search) {
		Agama.getList({ q: search }).then(function(agamas) {
			$scope.agamas = agamas;
		});
	};
	
	$scope.loadKelas = function(search) {
		Kelas.getList({ q: search }).then(function(kelass) {
			$scope.kelass = kelass;
		});
	};
	
	if (siswa) {
		$scope.siswa = siswa;
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
	$scope.submit = function(siswa, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (siswa.id) {
				siswa.put().then(success, error);
			} else {
				Siswa.post(siswa).then(success, error);
			}
		});
	};
}).controller('OrtuFormCtrl', function($scope, $modalInstance, $validation, Siswa, siswa) {
	if (siswa) {
		$scope.siswa = siswa;
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
	$scope.submit = function(siswa, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (siswa.id) {
				siswa.put().then(success, error);
			} else {
				Siswa.post(siswa).then(success, error);
			}
		});
	};
}).controller('testCtrl', ['$scope', function($scope){
	  $scope.content = '';
	  $scope.htmlContent = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
}]);
