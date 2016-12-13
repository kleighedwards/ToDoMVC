var app = angular.module('ngTodo');

app.factory('todoService', function($http, authenticationService) {
  var todoService = {};

  // Old Get Todos
  // todoService.getTodos = function(){
  //   return $http({
  //     method : 'GET',
  //     url : 'api/todo'
  //   })
  // };

  // New Get Todos
  todoService.getTodos = function(){
    var userId = null;
    if (authenticationService.isLoggedIn()) {
      userId = authenticationService.currentUser().id;
    }
    return $http({
      method : 'GET',
      url : 'api/user/' + userId + '/todos',
      headers : {
        'x-access-token' : authenticationService.getToken()
      }
    })
  };

  // Old Create Method
  // todoService.createTodo = function(newTask) {
  //   return $http({
  //     method : 'POST',
  //     url : 'api/todo',
  //     headers : {'Content-Type' : 'application/json'},
  //     data : {task : newTask, completed : false}
  //   })
  // };

  // New Create Method
  todoService.createTodo = function(newTask) {
    var userId = null;
    if (authenticationService.isLoggedIn()) {
      userId = authenticationService.currentUser().id;
    }
    return $http({
      method : 'POST',
      url : 'api/user/' + userId + '/todos',
      headers : {
        'Content-Type': 'application/json',
        'x-access-token' : authenticationService.getToken()
      },
      data : { task : newTask, completed : false }
    })
  };

  // Old Delete Method
  // todoService.deleteTodo = function(todo) {
  //   return $http({
  //     method : 'DELETE',
  //     url : 'api/todo/' + todo.id,
  //   })
  // };

  // New Delete Method
  todoService.deleteTodo = function(todo) {
    var userId = null;
    if (authenticationService.isLoggedIn()) {
      userId = authenticationService.currentUser().id;
    }
    return $http({
      method : 'DELETE',
      url : 'api/user/' + userId + '/todos/' + todo.id,
      headers : {
        'x-access-token' : authenticationService.getToken()
      }
    })
  };

  // Old Update Method
  // todoService.updateTodo = function(todo) {
  //   return $http({
  //     method : 'PUT',
  //     url : 'api/todo/' + todo.id,
  //     headers : {'Content-Type' : 'application/json'},
  //     data : todo
  //   })
  // }

  // New Update Method
  todoService.updateTodo = function(todo) {
    var userId = null;
    if (authenticationService.isLoggedIn()) {
      userId = authenticationService.currentUser().id;
    }
    return $http({
      method : 'PUT',
      url : 'api/user/' + userId + '/todos/' + todo.id,
      headers : {
        'Content-Type' : 'application/json',
        'x-access-token' : authenticationService.getToken()
      },
      data : todo
    })
  };

  return todoService;

});
