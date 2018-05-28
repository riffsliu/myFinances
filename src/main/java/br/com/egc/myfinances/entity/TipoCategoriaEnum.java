package br.com.egc.myfinances.entity;

import lombok.Getter;
import lombok.Setter;

public enum TipoCategoriaEnum {

	DESPESAS(0, "DESPESAS"), RENDAS(1, "RENDAS");

	@Getter
	@Setter
	private Integer codigo;
	@Getter
	@Setter
	private String descricao;

	private TipoCategoriaEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;

	}

}
