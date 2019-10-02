package dev3.dev3project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogadoModel {
   
   private Long id;
   private String email;

 
   public static UsuarioLogadoModel converterParaUsuarioLogado(Usuario usuario) {
       return UsuarioLogadoModel.builder()
               .id(usuario.getId())
               .email(usuario.getEmail())
               .build();
   }
   
}
