'use strict';

describe('Controller: NewsCtrl', function () {

  // load the controller's module
  beforeEach(module('yamaAppApp'));

  var RoleCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RoleCtrl = $controller('NewsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
