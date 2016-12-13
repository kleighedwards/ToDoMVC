var app = angular.module('ngTodo');

app.directive('todoRow', function($compile, todoService){
  return {
    restrict : 'A',
    template :
    ` <td ng-class="strike(data.completed)">{{data.task}}</td>
      <td><input type="checkbox" ng-model="data.completed" ng-change="update(data)"/></td>
      <td><button ng-click="edit(data)">Edit</button></td>
      <td><button ng-click="remove(data)">Delete</button></td>`,
    scope : {
      data : "=",
      remove : "=",
      update : "="
    },
    link : function($scope, $element, $attr){
      var $editForm = null;

      $scope.strike = function(completed) {
        if(completed) {
          return "strike";
        }
        else {
          return "clean";
        }
      }
      $scope.edit = function(todo) {
        if ($editForm === null) {
          $scope.editTodo = angular.copy(todo);

          $editForm =
          `<tr>
            <td><input type="text" ng-model="editTodo.task"></td>
            <td></td>
            <td><button ng-click="save(editTodo)">Save</button></td>
            <td><button ng-click="cancel()">Cancel</button></td>
          </tr>`;

          $editForm = $compile($editForm)($scope);
          $element.after($editForm);
        }
      }

      $scope.save = function(todo) {
        $scope.update(todo);
        $editForm.remove();
        $editForm = null;
        $scope.editTodo = {};
      }

      $scope.cancel = function() {
        if ($editForm != null) {
          $editForm.remove();
          $editForm = null;
        }
      }
    }
  }
});
