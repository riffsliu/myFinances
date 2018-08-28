package br.com.egc.myfinances.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NEWUSUARIO")
@Audited
public class UsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
	@SequenceGenerator(name = "usuario_generator", sequenceName = "usuario_idusuario_seq", allocationSize = 1)
	private Long idUsuario;

	@Getter
	@Setter
	private String nomeUsuario;

	@Getter
	@Setter
	private String loginUsuario;

	@Getter
	@Setter
	private String emailUsuario;

	@Getter
	@Setter
	private String senhaUsuario;

}
