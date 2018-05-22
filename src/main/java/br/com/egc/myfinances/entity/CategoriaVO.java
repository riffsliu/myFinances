package br.com.egc.myfinances.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public static String PADRAO = "padrão";
	public static String EDUCACAO_MARIDO = "educacao marido";
	public static String EDUCACAO_ESPOSA = "educacao esposa";
	public static String EDUCACAO_FILHO1 = "educacao filho 1";
	public static String ALIMENTACAO_TRABALHO = "alimentacao trabalho";
	public static String ALIMENTACAO_CASA = "alimentacao casa";
	public static String SAUDE = "saude";
	public static String TAXA_BANCARIA = "taxa bancaria";
	public static String CARTAO = "cartao";
	public static String EMPRESTIMO = "emprestimo";
	public static String SEGURO_VEICULO = "seguro veiculo";
	public static String FINANCIAMENTO_VEICULO = "financiamento veiculo";
	public static String INTERNET = "internet";
	public static String LUZ = "luz";
	public static String IPTU = "iptu";
	public static String CONDOMINIO = "condominio";
	public static String ROUPAS = "roupas";

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
	
	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCentroCusto")
	private CentroCustoVO centroCustoVO;

	public CategoriaVO() {

	}

	public CategoriaVO(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

}
