package br.com.egc.myfinances.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ResumoDTO {

	@Getter
	@Setter
	private String descricaoCategoria;
	@Getter
	@Setter
	private BigDecimal mes01;
	@Getter
	@Setter
	private BigDecimal mes02;
	@Getter
	@Setter
	private BigDecimal mes03;
	@Getter
	@Setter
	private BigDecimal mes04;
	@Getter
	@Setter
	private BigDecimal mes05;
	@Getter
	@Setter
	private BigDecimal mes06;
	@Getter
	@Setter
	private BigDecimal mes07;
	@Getter
	@Setter
	private BigDecimal mes08;
	@Getter
	@Setter
	private BigDecimal mes09;
	@Getter
	@Setter
	private BigDecimal mes10;
	@Getter
	@Setter
	private BigDecimal mes11;
	@Getter
	@Setter
	private BigDecimal mes12;

	@Getter
	@Setter
	private List<ResumoDTO> listResumoDTO;

	@Getter
	@Setter
	private BigDecimal totalMes01 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes02 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes03 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes04 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes05 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes06 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes07 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes08 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes09 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes10 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes11 = BigDecimal.ZERO;
	@Getter
	@Setter
	private BigDecimal totalMes12 = BigDecimal.ZERO;

}
