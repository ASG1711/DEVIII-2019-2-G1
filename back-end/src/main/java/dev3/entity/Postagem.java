package dev3.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;


@Entity
@Getter
@Setter
@Table(name = "POSTAGEM")
public class Postagem implements Serializable {

	private static final String SQ_NAME = "SQ_POSTAGEM";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
	@SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
	@Column(name = "ID_POSTAGEM")
	private Long id;

	@Basic(optional = false)
	@Column(name = "DS_TEXTO", nullable = true, length=4000)
	private String texto;

	@Basic(optional = true)
	@Column(name = "DS_IMAGEM", nullable = true)
	private String imagem;
        
        @Basic(optional = false)
	@Column(name = "BL_ISPRIVADA", nullable = true)
	private boolean isPrivada;
        
        @NotNull(message = "Field must not be null")
        @Basic(optional = false)
        @Column(name = "DS_DATAHORA",nullable = false)
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        private LocalDateTime hora;

        @ManyToOne
	private Usuario usuario;
        
        @ManyToMany(cascade = ALL)
        private List<Usuario> curtidas;

        @ManyToMany(cascade = ALL)
        private List<Comentario> comentarios;
        
        public Postagem(){
            
        }
        public Postagem(String texto,String imagem,LocalDateTime hora, Usuario usuario,boolean isPrivada){
            this.texto = texto;
            this.imagem = imagem;
            this.hora = hora;
            this.usuario = usuario;
            this.isPrivada = isPrivada;
        }
        
        public void atualizar(Postagem postagem){
            this.texto = postagem.texto;
            this.imagem = postagem.imagem;
            this.hora = postagem.hora;
            this.usuario = postagem.usuario;
            this.isPrivada = postagem.isPrivada;
        }

}
