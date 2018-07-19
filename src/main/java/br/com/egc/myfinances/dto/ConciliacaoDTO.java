package br.com.egc.myfinances.dto;

import br.com.egc.myfinances.entity.TransacaoVO;
import lombok.Getter;
import lombok.Setter;

public class ConciliacaoDTO {

	@Getter
	@Setter
	private int index;

	@Getter
	@Setter
	private TransacaoVO transacaoVO;

	@Getter
	@Setter
	private TransacaoVO transacaoOfx;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
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
		ConciliacaoDTO other = (ConciliacaoDTO) obj;
		if (index != other.index)
			return false;
		return true;
	}

}
