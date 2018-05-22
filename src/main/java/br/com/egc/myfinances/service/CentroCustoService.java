package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.CentroCustoDAO;
import br.com.egc.myfinances.entity.CentroCustoVO;

@Transactional
public class CentroCustoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CentroCustoDAO centroCustoDAO;

	public void criaCentroCustoDefault() {

		List<String> listString = new ArrayList<>();

		listString.add(CentroCustoVO.PADRAO);
		listString.add(CentroCustoVO.HABITACAO);
		listString.add(CentroCustoVO.ROUPAS);
		listString.add(CentroCustoVO.EDUCACAO);
		listString.add(CentroCustoVO.ALIMENTACAO);
		listString.add(CentroCustoVO.SAUDE);
		listString.add(CentroCustoVO.RECREACAO);
		listString.add(CentroCustoVO.TRANSPORTE);
		listString.add(CentroCustoVO.PAGAMENTOS);

		for (String string : listString) {

			CentroCustoVO centroCustoVO = new CentroCustoVO();
			centroCustoVO.setDescricaoCentroCusto(string);

			centroCustoDAO.criaCentroCusto(centroCustoVO);

		}

	}

}
