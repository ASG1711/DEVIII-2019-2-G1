package dev3.dto;

import dev3.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
public class UsuarioDto {
    public Long id;
    public String nome;
    public String apelido;
    public String senha;
    public String email;
    public String imagem;
    public List<Usuario> solicitacoes;
    public List<Usuario> amizades;
    public LocalDate dtNascimento;
    
}
