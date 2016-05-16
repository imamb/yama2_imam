'use strict';

angular.module('yamaApp').controller('PenjualanCtrl', function ($scope, $modal, $location, Penjualan, angularPopupBoxes,$cacheFactory, Produk) {
//angular.module('yamaApp').controller('PenjualanCtrl', function ($scope, $modal, $location, Penjualan, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	$scope.searchParams.hash = 0;
	$scope.page = 1;
//	$scope.TotalTransaksi = 0;

	// Search form submitted or page changed
	$scope.search = function() {
		$scope.searchParams.hash++;
		$scope.searchParams.page = $scope.page - 1;

		$location.search($scope.searchParams);

		// Load list on page loaded
		Penjualan.getList($scope.searchParams).then(function(penjualans) {
			$scope.penjualans = penjualans;
			$scope.page = penjualans.meta.number + 1;
//			$scope.TotalTransaksi = 0;
			angular.forEach(penjualans,function(penjualan){
				penjualan.getList('produks').then(function(produks){
					penjualan.produks=produks;
//					$scope.TotalTransaksi += penjualan.produks.harga;
//					console.log('Total '+ $scope.TotalTransaksi + ' ' +penjualan.produks.harga);
				});
			});
		});
	};

//	$scope.calcTotal = function(penjualan){
//	    return user.created * 10 + user.replied * 5 + user.read * 2;
//	  }
//
//	$scope.TotalTransaksi=function(penjualan){
//	      var summ=0;
//	      foreach( i : penjualan.produks)
//	      {
//	        summ=summ+Number(i.harga);
//	      }
//	      return summ;
//	    };



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
	
	$scope.addProduk = function(penjualan) {
		var modal = $modal.open({
			templateUrl: 'penjualan.produk.html',
			controller: 'PenjualanProdukFormCtrl',
			size: 'lg',
			resolve: {
				penjualan: function() {
					return penjualan;
				}
			}
		});

		modal.result.then(function() {
			$scope.search($scope.searchParams);
		});
	};

	var invalidateCache = function(penjualan) {
		$cacheFactory.get('$http').remove(penjualan.one('produks').getRequestedUrl());
	};
	
	$scope.removeProduk = function(penjualan, produk) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Data Penjualan Ini?<br>Data Transaksi : '+ penjualan.nomor +' : '+ penjualan.pembeli + '<br>Dengan Produk : '+ produk.kode + ' : '+ produk.nama).result.then(function() {
			penjualan.one('produks', produk.id).remove().then(function() {
				invalidateCache(penjualan);
				$scope.search();
			});
		});
	};
	
	$scope.removeAllProduk = function(penjualan) {
		penjualan.one('produks').remove().then(function() {
			invalidateCache(penjualan);
		});
	};
	
	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(penjualan) {
		angularPopupBoxes.confirm('Apakah Anda Yakin Akan Menghapus Data Penjualan Ini?\n'+ penjualan.nomor +'\n'+ penjualan.pembeli).result.then(function() {
			$scope.removeAllProduk(penjualan);
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
}).controller('PenjualanProdukFormCtrl', function($scope, $modalInstance, $cacheFactory, Produk, penjualan) {
	$scope.penjualan = penjualan;
	$scope.produks = [];

	var invalidateCache = function() {
		$cacheFactory.get('$http').remove(penjualan.one('produks').getRequestedUrl());
	};

	$scope.loadProduk = function(search) {
		Produk.getList({ q: search }).then(function(produks) {
			$scope.produks = produks;
		});
	};

	$scope.addProduk = function(produk) {
		penjualan.one('produks', produk.id).put().then(function() {
			invalidateCache();
		});
	};

	$scope.removeProduk = function(produk) {
		penjualan.one('produks', produk.id).remove().then(function() {
			invalidateCache();
		});
	};

	$scope.done = $modalInstance.close;
	//console.log('selesai');
});
