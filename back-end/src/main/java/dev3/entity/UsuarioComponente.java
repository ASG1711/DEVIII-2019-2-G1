package dev3.entity;

import dev3.entity.Usuario;
import dev3.entity.UsuarioLogadoModel;
import dev3.service.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UsuarioComponente {

    @Autowired
    UsuarioService usuarioServico;

    public UsuarioLogadoModel usuarioLogado() {
        return Optional
                .ofNullable(usuarioLogadoDetalhes())
                .map(UsuarioLogadoModel::converterParaUsuarioLogado)
                .orElse(null);
    }

    public Usuario usuarioLogadoDetalhes() {
        return Optional
                .ofNullable(user())
                .map(User::getUsername)
                .map(usuarioServico::findByEmail)
                .orElse(null);
    }

    private User user() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .orElse(null);
    }
}
