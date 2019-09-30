angular.module('app').controller('DashBoardController', function ($scope, $http, authService, usuariosService, postagemService,$localStorage,toastr) {
    
    $scope.user = $localStorage.usuarioLogado;
    $scope.auth = authService;
    let paginaAtual = 0;
    function listarSolicitacoes(){
        let promise = usuariosService.listarSolicitacoes();
        promise.then((response) => {
            $scope.solicitacoes = response;
            $scope.contemSolicitacao = hasSolicitacao;
        })
    }
    listarSolicitacoes();

//paginacao
    $scope.buscarPostagens = buscarPostagens;
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
    }
//fim paginacao
    function hasSolicitacao() {
        if($scope.solicitacoes.data.length > 0)
            return true;
        return false;
    }
    $scope.aceitaSolicitacao = function(id){
        let promise = usuariosService.aceitaSolicitacao(id);
        promise.then((response) => {
            listarSolicitacoes();
        },(response) => {
            listarSolicitacoes();
        })
    }
    $scope.removeSolicitacao = function(id){
        let promise = usuariosService.removeSolicitacao(id);
        promise.then((response) => {
            listarSolicitacoes();
        },(response) => {
            listarSolicitacoes();
        })
    }
    $scope.comentar = function(comentario) {
        postagemService.adicionarComentario(this.postagem.id,comentario).then((response) => {
            this.postagem = response.data;
            atualizaScopePostagem(response.data);
        },(response) => {
            console.log(this.postagem = response.data);
        });
    }

    function atualizaScopePostagem(postagem) {
        for(let i = 0; i < $scope.postagens.length; i++){
            if($scope.postagens[i].id === postagem.id){
                $scope.postagens[i] = postagem;
            }
        }
    }
    
    $scope.postarPost = function(postagem){
        if ($scope.formPostagem.$invalid) {
            toastr.error('Formulário inválido', 'Postar');
            return;
        }
        postagemService.adicionarPostagem(postagem).then((response)=> {
            toastr.success('Post efetuado com sucesso', 'Postar');
            paginaAtual = 0;
            listarPostagensAmigos(paginaAtual);
        },(response) => {
            //listarPostagensAmigos(paginaAtual);
        });
    }

    $scope.curtir = function(postagem) {
        postagemService.curtirPostagem(postagem.id)
                      .then(function (response) {
                        postagem.curtidas = response.data.curtidas;
                        verificaCurtidas(postagem);
                      },(response) => {
                        console.log(response);
                      })
    }
    
    $scope.editar = function(usuario){
       let promise = usuariosService.editar(usuario);
        promise.then((response) => {
            toastr.success('Você editou com sucesso', 'Editou');
            // $localStorage.usuarioLogado = response.data;
        })
    }
    

     function verificaCurtidas(postagem) {
        if(postagem.curtidas.find(x => x.id === $scope.user.id))
            return true; 
        return false;
    }
    $scope.verificarCurtida = verificaCurtidas;
});