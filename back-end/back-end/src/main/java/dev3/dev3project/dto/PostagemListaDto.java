package dev3.dev3project.dto;

import dev3.dev3project.entity.Comentario;
import dev3.dev3project.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PostagemListaDto {
    
    private Long id;
    
    private String texto; 
    
    private String imagem; 
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime hora;
    
    private List<Comentario> comentarios;
    
    private List<Usuario> curtidas;
    
    private UsuarioDto usuarioDto;
    
    private boolean isPrivado;
}

