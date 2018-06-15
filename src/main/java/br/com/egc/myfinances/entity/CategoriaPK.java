package br.com.egc.myfinances.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author ecarvalho
 *
 */
@Embeddable
public class CategoriaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name = "IDCATEGORIA")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
//	@SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_idcategoria_seq", allocationSize = 1)
	private Long idCategoria;

	@Getter
	@Setter
	@Column(name = "IDUSUARIO")
	private Long idUsuario;

	public CategoriaPK() {

	}

	public CategoriaPK(Long idUsuario, Long idCategoria) {
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaPK other = (CategoriaPK) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaPK [idCategoria=" + idCategoria + ", idUsuario=" + idUsuario + "]";
	}

	
	
	

}
