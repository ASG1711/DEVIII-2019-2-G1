package dev3.dev3project.entity;

import dev3.dev3project.dto.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final String SQ_NAME = "SQ_USUARIO";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
	@SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
	@Column(name = "ID_USUARIO")
	private Long id;

        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "USUARIO", length = 255,nullable = false)
	private String nome;
        
	@Basic(optional = true)
	@Column(name = "APELIDO", nullable = true, length = 50)
	private String apelido;
        
        @NotNull(message = "Campo obrigatório")
        @Basic(optional = false)
	@Column(name = "EMAIL", unique = true, nullable = false, length = 255)
	private String email;

        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "SENHA", nullable = false, length = 128)
	private String senha;
        
        @NotNull(message = "Campo obrigatório")
        @Basic(optional = false)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "NASCIMENTO", nullable = false)
	@Convert(converter = LocalDateConverter.class)
        private LocalDate dtNascimento;
        
        @Basic(optional = true)
	@Column(name = "IMAGEM", nullable = false)
	private String imagem;
        
        @JsonIgnore
        @ManyToMany(cascade = ALL)
        private List<Usuario> solicitacoes;
        
        @JsonIgnore
        @ManyToMany(cascade = ALL)
        private List<Usuario> amizades;
        
        public void atualizar(UsuarioDto usuario){
            this.nome = usuario.nome;
            this.apelido = usuario.apelido;
            this.imagem = usuario.imagem;
        }
        public Usuario(){
            
        }
        public Usuario(String nome, String apelido,String senha, String email, LocalDate dtNascimento, String imagem){
            this.nome = nome;
            this.apelido = apelido;
            this.senha = senha;
            this.email = email;
            this.dtNascimento = dtNascimento;
            this.imagem = imagem;
        }
        
        public UsuarioDto converteDto(){
            UsuarioDto retorno = new UsuarioDto();
            retorno.id = this.id;
            retorno.dtNascimento = this.dtNascimento;
            retorno.email = this.email;
            retorno.imagem = this.imagem;
            retorno.nome = this.nome;
            retorno.apelido = this.apelido;
            retorno.amizades = this.amizades;
            retorno.solicitacoes = this.solicitacoes;
            return retorno;
        }
        
        public void newSolicitacao(Usuario usuario){
            this.solicitacoes.add(usuario);
        }
        

}
