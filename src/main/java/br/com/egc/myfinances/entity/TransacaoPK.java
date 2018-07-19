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
public class TransacaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name = "IDTRANSACAO")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
//	@SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_idcategoria_seq", allocationSize = 1)
	private Long idTransacao;

	@Getter
	@Setter
	@Column(name = "IDUSUARIO")
	private Long idUsuario;
	
	@Getter
	@Setter
	@Column(name = "IDCONTA")
	private Long idConta;

	public TransacaoPK() {

	}

	public TransacaoPK(Long idTransacao, Long idUsuario,Long idConta) {
		this.idTransacao = idTransacao;
		this.idUsuario = idUsuario;
		this.idConta = idConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
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
		TransacaoPK other = (TransacaoPK) obj;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	

	
	
	

}
