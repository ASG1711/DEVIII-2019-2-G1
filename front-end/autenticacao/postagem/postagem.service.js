angular.module('app')
.factory('postagemService', function($http) {
    
    let urlBase = 'http://localhost:9090/postagem';

    function listar() {
        return $http.get(urlBase);
    }
    function buscar(id) {
        return $http.get(urlBase + "/" + id);
    }
    function editar(usuario) {
        return $http.put(urlBase + "/" + usuario.Id, usuario)
    }
    function apagar(id) {
        return $http.delete(urlBase + "/" + id);
    }
    /*function listarPostagensAmigos(){
        return $http.get(urlBase+"/postagensdash");
    }*/
    function listarPostagensAmigos(page){
        return $http.get(urlBase+"/postagensdash?page="+page+"&size=3");
    }
    function adicionarComentario(id,comentario){
        return $http.put(urlBase + "/salvacomentario/"+id, comentario)
    }
    function adicionarPostagem(postagem){
        return $http.post(urlBase+"/posta",postagem)
    }
    function curtirPostagem(id){
        return $http.post(urlBase+"/curtida/"+id);
    }
    function listarPostagens(id){
        return $http.get(urlBase+"/postagens/"+id);
    }
    return {
        listarPostagens: listarPostagens,
        curtirPostagem: curtirPostagem,
        adicionarPostagem: adicionarPostagem,
        adicionarComentario:adicionarComentario,
        listarPostagensAmigos:listarPostagensAmigos,
        listar: listar,
        buscar: buscar,
        editar: editar,
        apagar: apagar
    };
})