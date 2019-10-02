/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev3.dev3project.dto;

import dev3.dev3project.entity.Usuario;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dtNascimento;
    
}
