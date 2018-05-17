package br.com.egc.myfinances.dao;

import br.com.egc.myfinances.entity.CategoriaVO;

public class CategoriaDAO extends BaseDAO {

	public CategoriaVO buscarCategoriaPorId(Long primaryKey) {

		return getEntityManager().find(CategoriaVO.class, primaryKey);

	}

}
