package br.com.egc.myfinances.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NEWTRANSACAO")
public class TransacaoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_generator")
	@SequenceGenerator(name = "lancamento_generator", sequenceName = "lancamento_idlancamento_seq", allocationSize = 1)
	private Long idTransacao;

	@Getter
	@Setter
	private Long idTransacaoOriginal;

	@Getter
	@Setter
	private String tipoTransacao;

	@Getter
	@Setter
	private Date dataTransacao;

	@Getter
	@Setter
	private BigDecimal valorTransacao;

	@Getter
	@Setter
	private String descricaoTransacao;
	
	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUsuario")
	
	private UsuarioVO usuarioVO;

	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "idConta",referencedColumnName="idConta"),
		@JoinColumn(name = "idUsuarioConta",referencedColumnName="idUsuario")
		
	})
	private ContaVO contaVO;

	@Getter
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "idCategoria")
	@JoinColumns({
		@JoinColumn(name = "idCategoria",referencedColumnName="idCategoria"),
		@JoinColumn(name = "idUsuarioCategoria",referencedColumnName="idUsuario")
		
	})
	private CategoriaVO categoriaVO;

}
