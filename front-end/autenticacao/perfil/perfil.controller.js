angular.module('app').controller('PerfilController', function ($scope, $http, authService, usuariosService,$routeParams,$localStorage,postagemService) {
    
    $scope.auth = authService;
    atualizaLocalStorage();
    if($routeParams.idUser === undefined){
        $scope.user = $localStorage.usuarioLogado;
        $scope.ePropio = function(){
            return true;
        };
        listarPostagens($scope.user.id);
    }else{
        buscaUsuario();
        $scope.ePropio = function(){
            return false;
        };
        
    }

    function buscaUsuario(){
        usuariosService.buscar($routeParams.idUser).then((response) =>{
            $scope.user = response.data;
            listarPostagens($scope.user.id);
        });
    }

    $scope.removerAmizade = function(id){
        let promise = usuariosService.removeAmizade(id);
        promise.then((response) =>{
            buscaUsuario();
            atualizaLocalStorage();
        },(response) =>{
            buscaUsuario();
            atualizaLocalStorage();
        });
    }

    function atualizaLocalStorage(){
        let promise = usuariosService.atualizaLocalStorage();
        promise.then((response) => {
            $localStorage.usuarioLogado = response.data;
            console.log(response.data);
        });
    }

    function listarPostagens(id){
        let promise = postagemService.listarPostagens(id);
        promise.then((response) => {
            $scope.postagens = response.data.content;
        })
    }
    ///postagens/{id}

    $scope.comentar = function(comentario) {
        postagemService.adicionarComentario(this.postagem.id,comentario).then(function () {
            listarPostagens($scope.user.id);
        },(response) => {
            listarPostagens($scope.user.id);
        });
    }
    

    $scope.curtir = function(postagem) {
        postagemService.curtirPostagem(postagem.id)
                      .then(function (response) {
                        postagem.curtidas = response.data.curtidas;
                        verificaCurtidas(postagem);
                      },(response) => {
                        listarPostagens($scope.user.id);
                      })
    }
    
    $scope.editar = function(usuario){
       let promise = usuariosService.editar(usuario);
        promise.then((response) => {
            // $localStorage.usuarioLogado = response.data;
        })
    }
    

     function verificaCurtidas(postagem) {
        if(postagem.curtidas.find(x => x.id === $localStorage.usuarioLogado.id))
            return true; 
        return false;
    }
    $scope.verificarCurtida = verificaCurtidas;
    

    function isAmizade(id){
        for(let i = 0; i < $localStorage.usuarioLogado.amizades.length; i++){
            if($localStorage.usuarioLogado.amizades[i].id === id)
                return true;
        }
        return false;
    }
    $scope.isAmizade = isAmizade;
    function jaEnviou(){
        for(let i = 0 ; i < $scope.user.solicitacoes.length ; i++){
            if($scope.user.solicitacoes[i].id === $localStorage.usuarioLogado.id)
                return true;
        }
        return false;
    }

    function jaSolicitou(usuarioRecebido,id){
        for(let j = 0; j < usuarioRecebido.solicitacoes.length; j++){
            if(id === usuarioRecebido.solicitacoes[j].id){
                return true;
            }
        }
        return false;
    }

    $scope.aceitaSolicitacao = function(id){
        let promise = usuariosService.aceitaSolicitacao(id);
        promise.then((response) => {
            atualizaLocalStorage();
        },(response) => {
            atualizaLocalStorage();
        })
    }
    $scope.removeSolicitacao = function(id){
        let promise = usuariosService.removeSolicitacao(id);
        promise.then((response) => {
            atualizaLocalStorage();
        },(response) => {
            atualizaLocalStorage();
        })
    }

    $scope.enviadoSolicitacao = function(usuarioRecebido){
        if(jaSolicitou($localStorage.usuarioLogado,usuarioRecebido.id))
            return true;
        return false;
    }

    $scope.isValido = function(id){
        if(!isAmizade(id) && !jaEnviou(id) && $localStorage.usuarioLogado.id !== id && !jaSolicitou($localStorage.usuarioLogado,id))
            return true;
        return false;
    }

    $scope.solicitacaoEnviada = function(id){
        if(!isAmizade(id) && jaEnviou(id))
        return true;
    
    return false;
    }

    $scope.enviarSolicitacao = function(id){
        let promise = usuariosService.enviarSolicitacao(id);
        promise.then((response) => {
            buscaUsuario();
            atualizaLocalStorage();
        },(response) => {
            buscaUsuario();
            atualizaLocalStorage();
        });
    }

});