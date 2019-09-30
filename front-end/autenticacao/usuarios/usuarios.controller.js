angular.module('app').controller('UsuariosController', function ($scope, $http, authService, usuariosService, toastr) {

  $scope.auth = authService;

  $scope.registrar = function (usuario) {
    if ($scope.formUsuario.$invalid) {
      return;
    }
    usuariosService
      .registrar(usuario)
      .then(function () {
        resetarForm();
        toastr.success('Você se cadastrou com sucesso', 'Registro');
      }, (response) => {
        toastr.error('Verifique os campos e tente novamente', 'Registro');
      }
      )
  }



  function resetarForm() {
    $scope.usuario = {};
    $scope.formUsuario.$setPristine();
    $scope.formUsuario.$setUntouched();
  }

});
