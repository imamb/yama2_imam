'use strict';

angular.module('yamaApp').controller('PenjualanCtrl', function ($scope, $modal, $location, Penjualan, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Penjualan.getList($scope.searchParams).then(function(penjualans) {
			$scope.penjualans = penjualans;
			$scope.page = penjualans.meta.number + 1;
			angular.forEach(penjualans,function(penjualan){
				penjualan.getList('produks').then(function(produks){
					penjualan.produks=produks;
					//console.log('List Kategori'+news.categorys);
				});
			});
		});
	};

	$scope.search();

	// ID clicked, open popup form dialog
	$scope.openForm = function(penjualan) {
		var modal = $modal.open({
			templateUrl: 'penjualan.form.html',
			controller: 'PenjualanFormCtrl',
			size: 'lg',
			resolve: {
				penjualan: function() {
					return penjualan;
				}
			}
		});

		modal.result.then(function(p) {
			$scope.searchParams.q = p.nomor;
			$scope.search();
		});
	};

	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(penjualan) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Data Penjualan Ini?\n'+ penjualan.nomor +'\n'+ penjualan.pembeli).result.then(function() {
			penjualan.remove().then(function() {
				$scope.search();
			});
		});
	};
}).controller('PenjualanFormCtrl', function($scope, $modalInstance, $validation, Penjualan, penjualan) {
	if (penjualan) {
		$scope.penjualan = penjualan;
	}

	var success = function(p) {
		$modalInstance.close(p);
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.submit = function(penjualan, form) {
		$validation.validate(form).success(function() {
			$scope.error = false;

			if (penjualan.id) {
				penjualan.put().then(success, error);
			} else {
				Penjualan.post(penjualan).then(success, error);
			}
		});
	};
});
