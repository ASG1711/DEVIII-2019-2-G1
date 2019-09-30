angular.module('app').config(function ($routeProvider) {

  $routeProvider

    // pública
    .when('/home', {
      controller: 'UsuariosController',
      templateUrl: 'usuarios/usuarios.html'
    })

    // pública
    .when('/login', {
      controller: 'LoginController',
      templateUrl: 'login/login.html'
    })

    .when('/dashboard', {
      controller: 'DashBoardController',
      templateUrl: 'dashboard/dashboard.html',
      resolve: {

        // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
        autenticado: function (authService) {
          return authService.isAutenticadoPromise();
        }
      }
    })

    .when('/usuariolistar', {
      controller: 'UsuarioListarController',
      templateUrl: 'usuarios/usuarioslistar.html',
      resolve: {

        // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
        autenticado: function (authService) {
          return authService.isAutenticadoPromise();
        }
      }
    })
    
    .when('/perfil/:idUser?', {
      controller: 'PerfilController',
      templateUrl: 'perfil/perfil.html',
      resolve: {

        // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
        autenticado: function (authService) {
          return authService.isAutenticadoPromise();
        }
      }
    })


    .otherwise('/home');
});
