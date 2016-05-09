'use strict';

angular.module('yamaApp').config(function(validationSchemaProvider) {
	var jurusanValidator = {
		kode: {
			'validations': 'required, minlength=2',
			'validate-on': 'blur',
			'messages': {
				'required': {
					'error': 'Kode cannot be blank',
					'success': 'Ok'
				},
				'minlength': {
					'error': 'Must be longer than 2 character',
					'success': 'Ok'
				}
			}
		}
	};

	validationSchemaProvider.set('jurusan', jurusanValidator);
});