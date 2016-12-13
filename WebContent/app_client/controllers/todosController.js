var app = angular.module('ngTodo');

app.controller('todosController', function($scope, todoService) {
  // Now initialize todos as an empty array
  $scope.todos = [];

  // Call get todos to populate array
  $scope.loadData = function() {
    todoService.getTodos()
    .then(function(response){
      $scope.todos = response.data;
    });
  }

  // New Submit Method
  $scope.submit = function(todoTask) {
    todoService.createTodo(todoTask)
    .then(function() {
      $scope.task = null;
      $scope.loadData();
    });
  }

  // New Remove
  $scope.remove = function(todo) {
    todoService.deleteTodo(todo)
    .then(function() {
      $scope.loadData();
    })
  }

  // New Update
  $scope.update = function(todo) {
    todoService.updateTodo(todo)
    .then(function() {
      $scope.loadData();
    })
  }

  $scope.todoLength = function(todos) {
    var incomplete = 0;
    todos.forEach(function(todo) {
      if (todo.completed === false) {
        incomplete++;
      }
    })
    return incomplete
  }

  $scope.styleTodo = function(length) {
    return (length > 3) ? "yellow" : "green"
  }

  $scope.loadData();
});
