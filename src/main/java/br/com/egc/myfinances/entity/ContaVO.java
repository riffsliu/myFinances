package br.com.egc.myfinances.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NEWCONTA")
@Audited
public class ContaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Getter
//	@Setter
//	@Id
//	@Column
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_generator")
//	@SequenceGenerator(name = "conta_generator", sequenceName = "conta_idconta_seq", allocationSize = 1)
//	private Long idConta;
	
	@Getter
	@Setter
	@EmbeddedId
	private ContaPK contaPK;

	@Getter
	@Setter
	private String nomeConta;

	@Getter
	@Setter
	private String descricaoConta;

	@Getter
	@Setter
	private BigDecimal saldoAtual;
	@Getter
	@Setter
	private BigDecimal saldoInicial;
	@Getter
	@Setter
	private Date dataSaldoInicial;

	@Getter
	@Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contaVO", cascade = CascadeType.ALL)
	private List<TransacaoVO> listTransacaoVO;

}
