var app = angular.module('ngTodo');

app.controller('registrationController', function($scope, $location, registrationService,
  authenticationService) {

  // Register New User Method
  $scope.register = function(user) {
    registrationService.createUser(user)
    .then(function(response){
      if (response.status === 201) {
        authenticationService.saveToken(response.data.jwt)
        return response;
      }
    })
    .then(function(response) {
      $location.path('/todos');
    })
  }

})
