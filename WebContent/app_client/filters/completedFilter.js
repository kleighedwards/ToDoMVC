var app = angular.module("ngTodo");

app.filter('completeFilter', function(){
  return function(todos, complete) {
    var results = [];
    todos.forEach(function(todo){
      if (todo.completed === false || complete === true) {
        results.push(todo);
      }
    });
    return results;
  }
});
