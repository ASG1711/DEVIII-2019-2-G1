package dev3.dev3project.service;

import dev3.dev3project.dto.UsuarioDto;
import dev3.dev3project.entity.Usuario;
import dev3.dev3project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Page<UsuarioDto> list(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        
        return usuarios.map(x ->  converterUsuarioParaDto(x));
    }

    public Usuario loadById(Long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario save(Usuario usuario) {
        
        if(usuario.getId() == null){
            final String senha = usuario.getSenha();
            usuario.setSenha(ENCODER.encode(senha));
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario findByEmail(String username) {
        return usuarioRepository.findByEmailIgnoreCase(username);
    }
    
    public Usuario buscarNome(String nome){
        return usuarioRepository.findByNome(nome);
    }
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
    
    public UsuarioDto converterUsuarioParaDto(Usuario usuario){
        return UsuarioDto.builder()
                        .id(usuario.getId())
                        .nome(usuario.getNome())
                        .apelido(usuario.getApelido())
                        .senha(usuario.getSenha())
                        .email(usuario.getEmail())
                        .amizades(usuario.getAmizades())
                        .solicitacoes(usuario.getSolicitacoes())
                        .imagem(usuario.getImagem())
                        .dtNascimento(usuario.getDtNascimento())
                        .build();
    }
    
    

}
