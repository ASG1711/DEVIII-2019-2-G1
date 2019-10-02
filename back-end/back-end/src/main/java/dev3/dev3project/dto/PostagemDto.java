/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev3.dev3project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;


public class PostagemDto {
    public String texto;
    public String imagem;
    public boolean isPrivado;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    public LocalDateTime hora;
    public PostagemDto(){
        hora = LocalDateTime.now();
    }
}
