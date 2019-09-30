/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev3.controller;

import dev3.dto.PostagemDto;
import dev3.dto.PostagemListaDto;
import dev3.entity.Comentario;
import dev3.entity.Postagem;
import dev3.entity.Usuario;
import dev3.entity.UsuarioComponente;
import dev3.entity.UsuarioLogadoModel;
import dev3.service.PostagemService;
import dev3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;
    @Autowired
    private UsuarioComponente usuarioComponente;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/posta")
    public PostagemListaDto postMember(@RequestBody PostagemDto postagemDto) {
        UsuarioLogadoModel modelUser = usuarioComponente.usuarioLogado();
        Usuario usuario = usuarioService.loadById(modelUser.getId());
        Postagem postagem = new Postagem(postagemDto.texto,postagemDto.imagem,postagemDto.hora, usuario, postagemDto.isPrivado);
        return postagemService.salvar(postagem);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id,@RequestBody PostagemDto postagemDto) {

        Postagem postagemAtualizar = postagemService.loadById(id);

        if (postagemAtualizar == null) {
            return badRequest().body("Não existe postagem com esse Id");
        }
        UsuarioLogadoModel modelUser = usuarioComponente.usuarioLogado();
        Usuario usuario = usuarioService.loadById(modelUser.getId());
        Postagem postagem = new Postagem(postagemDto.texto,postagemDto.imagem,postagemDto.hora, usuario, postagemDto.isPrivado);
        
        postagemAtualizar.atualizar(postagem);
        
        postagemService.salvar(postagemAtualizar);
        
        return ok(postagemAtualizar);

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Postagem postagemRemover = postagemService.loadById(id);

        if (postagemRemover == null) {
            return badRequest().body("Não existe postagem com esse Id");
        }
        postagemService.delete(postagemRemover);

        return ok(postagemRemover);

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarById(@PathVariable Long id) {

        Postagem postagem = postagemService.loadById(id);

        if (postagem == null) {
            return badRequest().body("Não existe postagem com esse Id");
        }

        return ok(postagem);

    }
    
    @GetMapping("/postagens/{id}")
    public Page<PostagemListaDto> findAllByUsuario(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           @PathVariable Long id) {
        
        return postagemService.listByUsuario(page, size, usuarioService.loadById(id));
    }
    @GetMapping("/postagensdash")
    public Page<PostagemListaDto> postagensDash(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size) {
        
        return postagemService.listaPostagemAmigosEPropia(page, size);
    }
    
    @PutMapping("/salvacomentario/{id}")
    public ResponseEntity salvaComentario(@PathVariable Long id,@RequestBody Comentario comentario){
        Postagem postagem = postagemService.loadById(id);
        Comentario coment = new Comentario(usuarioComponente.usuarioLogadoDetalhes(),comentario.getComentario());
        postagem.getComentarios().add(coment);
        PostagemListaDto retorno = postagemService.salvar(postagem);
        return ok(retorno);
    }
    
    @PostMapping("/curtida/{id}")
    public ResponseEntity curtiPostagem(@PathVariable Long id){
        Postagem postagem = postagemService.loadById(id);
        Usuario atual = usuarioComponente.usuarioLogadoDetalhes();
        if(postagem.getCurtidas().contains(atual)){
            postagem.getCurtidas().remove(atual);
        }else{
            postagem.getCurtidas().add(atual);
        }
        PostagemListaDto retorno = postagemService.salvar(postagem);
        return ok(retorno);
    }
}

