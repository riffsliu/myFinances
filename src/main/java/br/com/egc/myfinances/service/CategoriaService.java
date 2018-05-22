package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.CentroCustoDAO;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.CentroCustoVO;

@Transactional
public class CategoriaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
	private CentroCustoDAO centroCustoDAO;

	public void criarCategoriaDefault() {

		List<String> listString = new ArrayList<>();

		listString.add(CategoriaVO.PADRAO);
		listString.add(CategoriaVO.EDUCACAO_MARIDO);
		listString.add(CategoriaVO.EDUCACAO_ESPOSA);
		listString.add(CategoriaVO.EDUCACAO_FILHO1);
		listString.add(CategoriaVO.ALIMENTACAO_TRABALHO);
		listString.add(CategoriaVO.ALIMENTACAO_CASA);
		listString.add(CategoriaVO.SAUDE);
		listString.add(CategoriaVO.TAXA_BANCARIA);
		listString.add(CategoriaVO.CARTAO);
		listString.add(CategoriaVO.EMPRESTIMO);
		listString.add(CategoriaVO.SEGURO_VEICULO);
		listString.add(CategoriaVO.FINANCIAMENTO_VEICULO);
		listString.add(CategoriaVO.INTERNET);
		listString.add(CategoriaVO.LUZ);
		listString.add(CategoriaVO.IPTU);
		listString.add(CategoriaVO.CONDOMINIO);
		listString.add(CategoriaVO.ROUPAS);

		for (String string : listString) {

			CategoriaVO categoriaVO = new CategoriaVO();
			categoriaVO.setDescricaoCategoria(string);

			categoriaVO.setCentroCustoVO(centroCustoDAO.buscaCentroCustoPorId(CentroCustoVO.PADRAO1));

			categoriaDAO.criaCategoria(categoriaVO);

		}

	}

}
