package br.com.egc.myfinances.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;

public class TransacaoDAO extends BaseDAO {

	public TransacaoVO buscarTransacaoPorId(Long primaryKey) {

		return getEntityManager().find(TransacaoVO.class, primaryKey);

	}

	public List<TransacaoVO> listarTransacaoPorConta(ContaVO contaVO) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.contaVO = :pConta ");

		Query query = getEntityManager().createQuery(sql.toString());
		
		query.setParameter("pConta", contaVO);

		return query.getResultList();

	}

}
