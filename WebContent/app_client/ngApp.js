var app = angular.module('ngTodo', ['ngRoute'])
  .config(function($routeProvider) {
    // Route Configurations
    $routeProvider
    .when('/', {
      // Designate Behavior for Index Route
      templateUrl : '/public/views/home.view.html',
      controller : 'homeController'
    })
    .when('/login',{
      templateUrl: '/public/views/login.view.html',
      controller: 'loginController'
    })
    .when('/register',{
      templateUrl: '/public/views/register.view.html',
      controller: 'registrationController'
    })
    .when('/todos',{
      templateUrl: '/public/views/todos.view.html',
      controller: 'todosController'
    });
});
