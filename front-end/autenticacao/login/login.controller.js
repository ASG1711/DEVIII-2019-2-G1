angular.module('app').controller('LoginController', function ($scope, authService,toastr) {

  $scope.login = function (usuario) {
    authService.login(usuario)
      .then(
        function (response) {
          toastr.success('Você logou com sucesso', 'Login');

        },
        function (response) {
          toastr.error('Erro no login', 'Login');
        });
  };

});