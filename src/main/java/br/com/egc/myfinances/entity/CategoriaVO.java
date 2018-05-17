package br.com.egc.myfinances.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIA")
public class CategoriaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
	@SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_idcategoria_seq", allocationSize = 1)
	private Long idCategoria;

	@Getter
	@Setter
	private String descricaoCategoria;

}
