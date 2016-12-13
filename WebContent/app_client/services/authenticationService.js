var app = angular.module('ngTodo');

app.factory('authenticationService', function($window, $http) {
  var authenticationService = {};

  // Place JWT into local storage
  authenticationService.saveToken = function(token){
    $window.localStorage['todo-token'] = token;
  };

  // Retrieve JWT from local storage
  authenticationService.getToken = function() {
    return $window.localStorage['todo-token'];
  };

  // Contact the server, authenticate user credentials
  authenticationService.login = function(user) {
    return $http({
      method : 'POST',
      url : 'api/auth/login',
      headers : { 'Content-Type' : 'application/json' },
      data : user })
      .then(function(response){
        authenticationService.saveToken(response.data.jwt);
        return response;
      });
  };

  // Remove JWT from local storage
  authenticationService.logout = function() {
    $window.localStorage.removeItem('todo-token');
  };

  authenticationService.isLoggedIn = function() {
    var token = authenticationService.getToken();

    if (token) {
      var payload = JSON.parse($window.atob(token.split('.')[1]));

      return payload.exp > Date.now() / 1000;
    }
    else {
        return false;
    }
  };

  // Get current user from JWT
  authenticationService.currentUser = function() {
    if (authenticationService.isLoggedIn()) {
      var token = authenticationService.getToken();
      var payload = JSON.parse($window.atob(token.split('.')[1]));

      return {
        username : payload.username,
        id : payload.id
      };
    }
  };

  return authenticationService;
});
