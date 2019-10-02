package dev3.dev3project.service;
import dev3.dev3project.dto.PostagemListaDto;
import dev3.dev3project.entity.Postagem;
import dev3.dev3project.entity.Usuario;
import dev3.dev3project.entity.UsuarioComponente;
import dev3.dev3project.repository.PostagemRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PostagemService {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private PostagemRepository postagemRepository;
        @Autowired
        private UsuarioService usuarioService;
        @Autowired
        private UsuarioComponente usuarioComponente;
	

	public Page<Postagem> list(Pageable pageable) {
		return postagemRepository.findAll(pageable);
	}

	public Postagem loadById(Long id) {
		return postagemRepository.findOne(id);
	}
        
        public PostagemListaDto salvar(Postagem postagem) {
            Postagem retorno = postagemRepository.save(postagem);
		return converterPostagemParaPostagemListaDto(retorno);
	} 
        
        public void delete(Postagem postagem){
            postagemRepository.delete(postagem);
        }
        
        public Page<PostagemListaDto> listByUsuario(int page, int size, Usuario usuario) {
        
            Pageable pgbl = new PageRequest(page, size);

            Page<Postagem> postagens = postagemRepository.findAllByUsuarioOrderByHoraDesc(usuario, pgbl);
            
            return postagens.map(x ->  converterPostagemParaPostagemListaDto(x));
        }
        
        public Page<PostagemListaDto> listaPostagemAmigosEPropia(int page, int size){
            List<Long> ids = usuarioComponente.usuarioLogadoDetalhes().getAmizades()
                                            .stream().map(Usuario::getId)
                                            .collect(Collectors.toList());
            ids.add(usuarioComponente.usuarioLogadoDetalhes().getId());
             Pageable pgbl = new PageRequest(page, size);
             
             Page<Postagem> postagens = postagemRepository.findByUsuarioIdInAndIsPrivadaOrderByHoraDesc(ids, false, pgbl);
            return postagens.map(x ->  converterPostagemParaPostagemListaDto(x));
        }
        
        private PostagemListaDto converterPostagemParaPostagemListaDto(Postagem postagem){
            return PostagemListaDto.builder()
                        .id(postagem.getId())
                        .texto(postagem.getTexto())
                        .imagem(postagem.getImagem())
                        .hora(postagem.getHora())
                        .comentarios(postagem.getComentarios())
                        .curtidas(postagem.getCurtidas())
                        .usuarioDto(usuarioService.converterUsuarioParaDto(postagem.getUsuario()))
                        .isPrivado(postagem.isPrivada())
                        .build();
        }
	public Postagem curtida(Long id) {
            Postagem postagem = loadById(id);
            Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
            if(postagem.getCurtidas().stream().filter(x -> x.getId() == usuario.getId()).collect(Collectors.toList()).size() > 0){
                postagem.getCurtidas().remove(usuario); 
            }
            else {
                postagem.getCurtidas().add(usuarioComponente.usuarioLogadoDetalhes());
            }
        
            return postagemRepository.save(postagem); 
        }
}