angular.module('app')
.factory('usuariosService', function($http) {
    
    let urlBase = 'http://localhost:9090/usuario';

    function listar() {
        return $http.get(urlBase);
    }
    function buscar(id) {
        return $http.get(urlBase + "/" + id);
    }
    function registrar(usuario) { 
        return $http.post(urlBase+"/public/cadastro", usuario)            
    }
    function editar(usuario) {
        return $http.put(urlBase + "/" + usuario.id, usuario)
    }
    function apagar(id) {
        return $http.delete(urlBase + "/" + id);
    }
    function listarSolicitacoes() {
        return $http.get(urlBase+"/solicitacoes");
    }
    function aceitaSolicitacao(id) {
        return $http.put(urlBase+"/aceitasolicitacao", id);
    }
    function removeSolicitacao(id){
        return $http.put(urlBase+"/removesolicitacao", id);
    }
    function enviarSolicitacao(id) { 
        return $http.put(urlBase+"/solicitacao", id)            
    }
    function atualizaLocalStorage() {
        return $http.get(urlBase+"/login");
    }
    function removeAmizade(id){
        return $http.put(urlBase+"/deleteamizade",id);
    }
    return {
        removeAmizade: removeAmizade,
        atualizaLocalStorage: atualizaLocalStorage,
        enviarSolicitacao: enviarSolicitacao,
        removeSolicitacao: removeSolicitacao,
        aceitaSolicitacao: aceitaSolicitacao,
        listarSolicitacoes: listarSolicitacoes,
        listar: listar,
        buscar: buscar,
        registrar: registrar,
        editar: editar,
        apagar: apagar
    };
})