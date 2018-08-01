package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.ContaPK;
import br.com.egc.myfinances.entity.ContaVO;

@Transactional
public class DashboardService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContaDAO contaDAO;

	@Inject
	private TransacaoDAO transacaoDAO;

	public BigDecimal buscarTotalDespesas(String mesAnoSelecionado) {

		BigDecimal total = transacaoDAO.buscarTotalDespesas(mesAnoSelecionado);
		if (total == null) {
			return BigDecimal.ZERO;
		} else {
			return total;

		}
	}

	public BigDecimal buscarTotalRendas(String mesAnoSelecionado) {

		BigDecimal total = transacaoDAO.buscarTotalRendas(mesAnoSelecionado);
		if (total == null) {
			return BigDecimal.ZERO;
		} else {
			return total;

		}
	}

	public List<ContaVO> listarConta() {

		return contaDAO.listarConta();

	}

}
