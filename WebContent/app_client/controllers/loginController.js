var app = angular.module('ngTodo');

app.controller('loginController', function($scope, $location, authenticationService) {

  // Login User Method
  $scope.login = function(username, password) {
    var user = {username : username, password : password};
    authenticationService.login(user)
    .then(function(response){
      if (response.status === 200) {
        $location.url('/todos');
      }
    })
  }

});
