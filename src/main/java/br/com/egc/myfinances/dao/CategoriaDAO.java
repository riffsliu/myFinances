package br.com.egc.myfinances.dao;

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

}
