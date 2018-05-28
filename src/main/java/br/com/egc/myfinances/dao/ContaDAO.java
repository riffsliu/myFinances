package br.com.egc.myfinances.dao;

import java.math.BigDecimal;

import br.com.egc.myfinances.entity.ContaVO;

public class ContaDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void criaContaDefault() {
		ContaVO contaVO = new ContaVO();
		contaVO.setNomeConta("itau");
		contaVO.setDescricaoConta("conta corrente");
		contaVO.setSaldoConta(new BigDecimal("-39.14"));

		getEntityManager().getTransaction().begin();
		getEntityManager().persist(contaVO);
		getEntityManager().getTransaction().commit();
		getEntityManager().close();

	}

	public void criaConta(ContaVO contaVO) {

		getEntityManager().persist(contaVO);

	}

	public ContaVO buscarContaPorId(Long primaryKey) {

		return getEntityManager().find(ContaVO.class, primaryKey);

	}

}
