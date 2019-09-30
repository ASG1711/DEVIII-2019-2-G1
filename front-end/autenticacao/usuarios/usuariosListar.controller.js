angular.module('app').controller('UsuarioListarController', function ($scope, $http, authService, usuariosService, $localStorage) {
    
    $scope.auth = authService;
    var usuario = $localStorage.usuarioLogado;
    var paginaAtual = 0;
    function listarUsuarios(page){
        let promise = usuariosService.listar(page);
        promise.then((response) => {
            $scope.usuarios = response.data.content;
        });
    }
    atualizaLocalStorage();

    function atualizaLocalStorage(){
        let promise = usuariosService.atualizaLocalStorage();
        promise.then((response) => {
            $localStorage.usuarioLogado = response.data;
            usuario = response.data;
            listarUsuarios();
        });
    }

    //paginacao
    /*$scope.buscarPostagens = buscarPostagens;
    listarPostagensAmigos(paginaAtual);
    function listarPostagensAmigos(pagina) {
        console.log($scope.postagens);
        postagemService.listarPostagensAmigos(pagina)
        .then(function (response) {
            if(pagina > 0) {
                response.data.content.forEach(x => $scope.postagens.push(x));
            }
            else{
                $scope.postagens = response.data.content;
            }
            $scope.ultimaPagina = response.data.last;
            paginaAtual = response.data.number;
        })
    }
    
    function buscarPostagens() {
        listarPostagensAmigos(paginaAtual + 1);
    }*/

    //fim paginacao

    $scope.enviarSolicitacao = function(id){
        let promise = usuariosService.enviarSolicitacao(id);
        promise.then((response) => {
            listarUsuarios();
        },(response) => {
            listarUsuarios();
        });
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

    function isAmigo(id){
        for(let i = 0 ; i < usuario.amizades.length; i++){
            if(usuario.amizades[i].id === id){
                return true;
            }
            
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

    $scope.enviadoSolicitacao = function(usuarioRecebido){
        if(jaSolicitou(usuario,usuarioRecebido.id))
            return true;
        return false;
    }

    $scope.isValido = function(usuarioRecebido){
        
        if(usuarioRecebido.id === usuario.id)
            return false;
        
        if(isAmigo(usuarioRecebido.id))
            return false;
        
        if(jaSolicitou(usuarioRecebido,usuario.id))
            return false;

        if(jaSolicitou(usuario,usuarioRecebido.id))
            return false;
        
        return true;
    }

    function isAmizade(id){
        for(let i = 0; i < $localStorage.usuarioLogado.amizades.length; i++){
            if($localStorage.usuarioLogado.amizades[i].id === id)
                return true;
        }
        return false;
    }
    $scope.isAmizade = isAmizade;
    function jaEnviou(user){
        for(let i = 0 ; i < user.solicitacoes.length ; i++){
            if(user.solicitacoes[i].id === $localStorage.usuarioLogado.id)
                return true;
        }
        return false;
    }

    $scope.removerAmizade = function(id){
        let promise = usuariosService.removeAmizade(id);
        promise.then((response) =>{
            atualizaLocalStorage();
        },(response) =>{
            atualizaLocalStorage();
        });
    }

    $scope.solicitacaoEnviada = function(user){
        if(!isAmizade(user.id) && jaEnviou(user))
        return true;
    
    return false;
    }
});