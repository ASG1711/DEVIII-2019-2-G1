package dev3.controller;


import dev3.dto.UsuarioDto;
import dev3.entity.Usuario;
import dev3.entity.UsuarioComponente;
import dev3.service.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victor.scherer
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioComponente usuarioComponente;

    //@Secured("ROLE_ADMIN")
    @GetMapping("/login")
    public ResponseEntity buscarUsuarioLogado() {
        Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
        return ResponseEntity.ok(usuario.converteDto());
    }

    @PostMapping("/public/cadastro")
    public Usuario postMember(@RequestBody UsuarioDto usuarioDto) {
        usuarioDto.id = null;
        Usuario usuario = new Usuario(usuarioDto.nome,usuarioDto.apelido,usuarioDto.senha,usuarioDto.email,usuarioDto.dtNascimento,usuarioDto.imagem);
        
        return usuarioService.save(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id,@RequestBody UsuarioDto usuarioDto) {

        Usuario usuarioAtualizar = usuarioService.loadById(id);

        if (usuarioAtualizar == null) {
            return badRequest().body("Não existe usuario com esse Id");
        }
       
        usuarioAtualizar.atualizar(usuarioDto);
        
        usuarioService.save(usuarioAtualizar);
        
        return ok(usuarioAtualizar);

    }
    
    @PutMapping("/solicitacao")
    public ResponseEntity solicitarAmizade(@RequestBody long idSolicitado) {

        Usuario usuarioAtualizar = usuarioService.loadById(idSolicitado);

        if (usuarioAtualizar == null) {
            return badRequest().body("Não existe usuario com esse Id");
        }
        usuarioAtualizar.newSolicitacao(usuarioComponente.usuarioLogadoDetalhes());
        
        usuarioService.save(usuarioAtualizar);
        
        return ok("atualizado");

    }
    
    @PutMapping("/aceitasolicitacao")
    public ResponseEntity aceitarAmizade(@RequestBody Long idSolicitado) {

        Usuario usuario = usuarioService.loadById(usuarioComponente.usuarioLogadoDetalhes().getId());
        Usuario usuarioSolicitado = usuarioService.loadById(idSolicitado);

        usuario.getSolicitacoes().remove(usuarioSolicitado);

        usuarioSolicitado.getAmizades().add(usuario);
        usuario.getAmizades().add(usuarioSolicitado);

        usuarioService.save(usuarioSolicitado);
        usuarioService.save(usuario); 
        return ok("deveria funcionar");
    }
    
    @PutMapping("/removesolicitacao")
    public ResponseEntity removeSolicitacao(@RequestBody Long idSolicitado) {

        Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
        Usuario usuarioSolicitado = usuarioService.loadById(idSolicitado);

        usuario.getSolicitacoes().remove(usuarioSolicitado);
        usuarioService.save(usuario); 
        return ok("deveria funcionar");
    }
    
    @PutMapping("/deleteamizade")
    public ResponseEntity deleteAmizade(@RequestBody Long id) {

        Usuario usuario = usuarioService.loadById(usuarioComponente.usuarioLogadoDetalhes().getId());
        Usuario usuarioSolicitado = usuarioService.loadById(id);
        usuarioSolicitado.getAmizades().remove(usuario);
        usuario.getAmizades().remove(usuarioSolicitado);

        usuarioService.save(usuarioSolicitado);
        usuarioService.save(usuario); 
        return ok("");

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Usuario usuarioRemover = usuarioService.loadById(id);
        

        if (usuarioRemover == null) {
            return badRequest().body("Não existe usuario com esse Id");
        }
        usuarioService.delete(usuarioRemover);

        return ok("Excluido com sucesso");

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarById(@PathVariable Long id) {

        Usuario usuario = usuarioService.loadById(id);
        
        UsuarioDto retorno = usuario.converteDto();

        if (usuario == null) {
            return badRequest().body("Não existe usuario com esse Id");
        }
        return ok(retorno);

    }
    @GetMapping()
    public ResponseEntity buscarTodos(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size) {
        
        Pageable pgbl = new PageRequest(page, size);
        return ok(usuarioService.list(pgbl));

    }
    @GetMapping("/amizades")
    public ResponseEntity listaAmizades(){
        Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
        //usuario.retornaSemListaAmizade();
        return ok(usuario.getAmizades());
    }
    
    @GetMapping("/solicitacoes")
    public ResponseEntity listaSolicitacoes(){
        Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
        //usuario.retornaSemListaSolicitacao();
        return ok(usuario.getSolicitacoes());
    }
}
