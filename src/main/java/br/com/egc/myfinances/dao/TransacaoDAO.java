package br.com.egc.myfinances.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;

public class TransacaoDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransacaoVO buscarTransacaoPorId(Long primaryKey) {

		return getEntityManager().find(TransacaoVO.class, primaryKey);

	}

	@SuppressWarnings("unchecked")
	public List<TransacaoVO> listarTransacaoPorConta(ContaVO contaVO) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.contaVO = :pConta ");

		Query query = getEntityManager().createQuery(sql.toString());

		query.setParameter("pConta", contaVO);

		return query.getResultList();

	}

	public void criaTransacao(TransacaoVO transacaoVO) {

		getEntityManager().persist(transacaoVO);

	}

	public Boolean existeTransacao(Long idTransacaoOriginal) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.idTransacaoOriginal = :pidTransacaoOriginal ");

		Query query = getEntityManager().createQuery(sql.toString());

		query.setParameter("pidTransacaoOriginal", idTransacaoOriginal);

		TransacaoVO transacaoVO = null;
		try {

			transacaoVO = (TransacaoVO) query.getSingleResult();
		} catch (NoResultException noResultException) {

		}

		if (transacaoVO == null) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;

		}

	}

	public List<TransacaoVO> listarTransacaoCategoriaDefault() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.idCategoria=1 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();

	}

	public void atualizaTransacao(TransacaoVO transacaoVO) {

		getEntityManager().merge(transacaoVO);

	}

	public List<TransacaoVO> listarTransacaoRendas() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=1 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}

	public List<TransacaoVO> listarTransacaoDespesas() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}

}
