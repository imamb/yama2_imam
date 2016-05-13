'use strict';

angular.module('yamaApp').controller('PembelianCtrl', function ($scope, $modal, $location, Pembelian, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Pembelian.getList($scope.searchParams).then(function(pembelians) {
			$scope.pembelians = pembelians;
			$scope.page = pembelians.meta.number + 1;
			angular.forEach(pembelians,function(pembelian){
				pembelian.getList('produks').then(function(produks){
					pembelian.produks=produks;
					//console.log('List Kategori'+news.categorys);
				});
			});
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(pembelian) {
		var modal = $modal.open({
			templateUrl: 'pembelian.form.html',
			controller: 'PembelianFormCtrl',
			size: 'lg',
			resolve: {
				pembelian: function() {
					return pembelian;
				}
			}
		});

		modal.result.then(function(p) {
			$scope.searchParams.q = p.nomor;
			$scope.search();
		});
	};

	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(pembelian) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Data Pembelian Ini?\n'+ pembelian.nomor +'\n'+ pembelian.penjual).result.then(function() {
			pembelian.remove().then(function() {
				$scope.search();
			});
		});
	};
}).controller('PembelianFormCtrl', function($scope, $modalInstance, $validation, Pembelian, pembelian) {
	if (pembelian) {
		$scope.pembelian = pembelian;
	}

	var success = function(p) {
		$modalInstance.close(p);
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.submit = function(pembelian, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (pembelian.id) {
				pembelian.put().then(success, error);
			} else {
				Pembelian.post(pembelian).then(success, error);
			}
		});
	};
});
