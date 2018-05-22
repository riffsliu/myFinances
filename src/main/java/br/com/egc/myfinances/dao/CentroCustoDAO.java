package br.com.egc.myfinances.dao;

import br.com.egc.myfinances.entity.CentroCustoVO;

public class CentroCustoDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CentroCustoVO buscaCentroCustoPorId(Long primaryKey) {
		
		return getEntityManager().find(CentroCustoVO.class, primaryKey);
	}

	public void criaCentroCusto(CentroCustoVO centroCustoVO) {

		getEntityManager().persist(centroCustoVO);

	}


}
