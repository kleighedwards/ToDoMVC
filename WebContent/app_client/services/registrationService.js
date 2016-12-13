var app = angular.module('ngTodo');

app.factory('registrationService', function($http) {
  var registrationService = {};

  // Create User Method
  registrationService.createUser = function(user) {
    return $http({
      method : 'POST',
      url : 'api/auth/signup',
      headers : {'Content-Type' : 'application/json'},
      data : {username : user.username, email : user.email, password : user.password}
    })
  };

  return registrationService;
});
