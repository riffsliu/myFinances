package br.com.egc.myfinances.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaPK;
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

	public ContaVO buscarContaPorId(ContaPK contaPK) {

		return getEntityManager().find(ContaVO.class, contaPK);

	}

	public List<ContaVO> listarConta() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT contaVO FROM ContaVO contaVO ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}
	
	public Long nextIdConta(Long idUsuario) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT max(contaVO.contaPK.idConta) FROM ContaVO contaVO ");
		sql.append("WHERE contaVO.contaPK.idUsuario = :pIdUsuario ");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		query.setParameter("pIdUsuario", idUsuario);
		
		Long result = (Long) query.getSingleResult(); 
		
		if(result==null) {
			return 1L;
		}
		
		return result+1;
	}

	public void atualizaConta(ContaVO contaVO) {
		
		getEntityManager().merge(contaVO);
		
	}

}
