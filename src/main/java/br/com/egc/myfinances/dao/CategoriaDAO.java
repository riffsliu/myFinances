package br.com.egc.myfinances.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.egc.myfinances.entity.CategoriaVO;

public class CategoriaDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaVO buscarCategoriaPorId(Long primaryKey) {

		return getEntityManager().find(CategoriaVO.class, primaryKey);

	}

	public void criaCategoria(CategoriaVO categoriaVO) {

		getEntityManager().persist(categoriaVO);

	}

	public List<CategoriaVO> listarCategoriaDespesas() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT categoriaVO FROM CategoriaVO categoriaVO ");
		sql.append("WHERE categoriaVO.tipoCategoriaEnum = 0 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}

	public List<CategoriaVO> listarCategoriaRendas() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT categoriaVO FROM CategoriaVO categoriaVO ");
		sql.append("WHERE categoriaVO.tipoCategoriaEnum = 1 ");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		return query.getResultList();
	}

}
