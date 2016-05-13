'use strict';

angular.module('yamaApp').controller('ProdukCtrl', function ($scope, $modal, $location, Produk, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Produk.getList($scope.searchParams).then(function(produks) {
			$scope.produks = produks;
			$scope.page = produks.meta.number + 1;
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(produk) {
		var modal = $modal.open({
			templateUrl: 'produk.form.html',
			controller: 'ProdukFormCtrl',
			size: 'lg',
			resolve: {
				produk: function() {
					return produk;
				}
			}
		});

		modal.result.then(function(p) {
			$scope.searchParams.q = p.nama;
			$scope.search();
		});
	};

	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(produk) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Data Produk Ini?\n'+ produk.nama +'\n'+ produk.keterangan).result.then(function() {
			produk.remove().then(function() {
				$scope.search();
			});
		});
	};
}).controller('ProdukFormCtrl', function($scope, $modalInstance, $validation, Produk, produk) {
	if (produk) {
		$scope.produk = produk;
	}

	var success = function(p) {
		$modalInstance.close(p);
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.submit = function(produk, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (produk.id) {
				produk.put().then(success, error);
			} else {
				Produk.post(produk).then(success, error);
			}
		});
	};
});
