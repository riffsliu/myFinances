package br.com.egc.myfinances.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NEWCATEGORIA")
@Audited
public class CategoriaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String PADRAO = "padrão";
	public static String SALARIO = "salário";
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

//	@Getter
//	@Setter
//	@Id
//	@Column
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_generator")
//	@SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_idcategoria_seq", allocationSize = 1)
//	private Long idCategoria;
	
	@Getter
	@Setter
	@EmbeddedId
	private CategoriaPK categoriaPK;
	
	
	
	@Getter
	@Setter
	private String descricaoResumida;

	@Getter
	@Setter
	private String descricaoCategoria;

	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCentroCusto")
	private CentroCustoVO centroCustoVO;

	@Getter
	@Setter
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "IDTIPOCATEGORIA")
	private TipoCategoriaEnum tipoCategoriaEnum;

	public CategoriaVO() {

	}

	public CategoriaVO(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaPK == null) ? 0 : categoriaPK.hashCode());
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
		CategoriaVO other = (CategoriaVO) obj;
		if (categoriaPK == null) {
			if (other.categoriaPK != null)
				return false;
		} else if (!categoriaPK.equals(other.categoriaPK))
			return false;
		return true;
	}

	

}
