package dev3.entity;


import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@Table(name = "COMENTARIO")
public class Comentario implements Serializable {

	private static final String SQ_NAME = "SQ_COMENTARIO";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
	@SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
	@Column(name = "ID_COMENTARIO")
	private Long id;

	@Basic(optional = false)
        @OneToOne
        private Usuario usuario;

        @Basic(optional = false)
        @Column(name = "COMENTARIO", nullable = false, length = 512)
        private String comentario;
        
        public Comentario(){
            
        }
        
        public Comentario(Usuario usuario,String comentario){
            this.usuario = usuario;
            this.comentario = comentario;
        }
        
        public void atualizar(Comentario comentario){
            this.usuario = comentario.usuario;
            this.comentario = comentario.comentario;
        }

}