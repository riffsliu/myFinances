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
@Table(name = "CENTROCUSTO")
public class CentroCustoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Long PADRAO1 = 1L;
	public static String PADRAO = "padrao";
	public static String HABITACAO = "habitação";
	public static String ROUPAS = "roupas";
	public static String EDUCACAO = "educação";
	public static String ALIMENTACAO = "alimentação";
	public static String SAUDE = "saude";
	public static String RECREACAO = "recreação";
	public static String TRANSPORTE = "transporte";
	public static String PAGAMENTOS = "pagamentos";

	@Getter
	@Setter
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centrocusto_generator")
	@SequenceGenerator(name = "centrocusto_generator", sequenceName = "centrocusto_idcentrocusto_seq", allocationSize = 1)
	private Long idCentroCusto;

	@Getter
	@Setter
	private String descricaoCentroCusto;

	public CentroCustoVO() {

	}

}
