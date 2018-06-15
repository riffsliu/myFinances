package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.ContaPK;
import br.com.egc.myfinances.entity.ContaVO;

@Transactional
public class ContaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContaDAO contaDAO;

	public List<ContaVO> listarConta() {

		return contaDAO.listarConta();

	}

	public void salvarConta(ContaVO contaVO) {

		ContaPK contaPK = new ContaPK(1L, contaDAO.nextIdConta(1L));
		contaPK.setIdUsuario(1L);
		contaVO.setContaPK(contaPK);

		contaDAO.criaConta(contaVO);
	}

	public void criaContaDefault() {

		ContaVO contaVO = new ContaVO();
		contaVO.setDescricaoConta("corrente");
		contaVO.setNomeConta("itau");
		contaVO.setSaldoConta(new BigDecimal("0"));

		contaDAO.criaConta(contaVO);
	}

}
