var app = angular.module('ngTodo');

app.directive('myFooter', function(){
    return {
      restrict : 'E',
      template :
      `<footer>
        <h3>
          Todo's Completed:
          <span ng-class="styleComplete(completedLength(data))">{{completedLength(data)}}</span>
        </h3>
      </footer>`,
      scope : {
        data : "=",
      },
      link : function($scope, $element, $attr){
        $scope.completedLength = function(data) {
          var completed = 0;
          data.forEach(function(todo) {
            if (todo.completed === true) {
              completed++;
            }
          })
          return completed
        }
        $scope.styleComplete = function(length) {
          return (length >= 1) ? "green" : "yellow"
        }

      }
    }
  });
