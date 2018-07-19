package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.controller.BaseBean;
import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.dao.UsuarioDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.entity.UsuarioVO;

@Transactional
public class TransacaoService extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TransacaoDAO transacaoDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
	private ContaDAO contaDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	public void atualizarTransacao(List<TransacaoVO> listTransacaoVO) {

		for (TransacaoVO transacaoVO : listTransacaoVO) {

			transacaoDAO.atualizaTransacao(transacaoVO);

		}

	}

	public List<TransacaoVO> listarTransacaoRendas(String mesAnoSelecionado) {

		return transacaoDAO.listarTransacaoRendas(mesAnoSelecionado);

	}

	public List<TransacaoVO> listarTransacaoDespesas(String mesAnoSelecionado) {

		return transacaoDAO.listarTransacaoDespesas(mesAnoSelecionado);

	}

	public List<TransacaoVO> listarTransacaoTodas(String mesAnoSelecionado) {

		return transacaoDAO.listarTransacaoTodas(mesAnoSelecionado);

	}

	public List<TransacaoVO> listarTransacao(String mesAnoSelecionado, CategoriaPK categoriaPK) {

		return transacaoDAO.listarTransacao(mesAnoSelecionado, categoriaPK);
	}

	public void adicionarTransacao(TransacaoVO transacaoVO) {

		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(Util.getUsuarioNaSession().getEmailUsuario());

		ContaVO contaVO = contaDAO.buscarContaPorId(Util.getContaNaSession().getContaPK());

		transacaoVO.setUsuarioVO(usuarioVO);
		transacaoVO.setContaVO(contaVO);

		transacaoVO.setCategoriaVO(categoriaDAO.buscarCategoriaPorId(transacaoVO.getCategoriaVO().getCategoriaPK()));

		contaVO.setSaldoConta(contaVO.getSaldoConta().add(transacaoVO.getValorTransacao()));

		transacaoDAO.adicionarTransacao(transacaoVO);

		contaDAO.atualizaConta(contaVO);

	}

	public void removerTransacao(TransacaoVO transacaoVO) {

		ContaVO contaVO = contaDAO.buscarContaPorId(Util.getContaNaSession().getContaPK());
		contaVO.setSaldoConta(contaVO.getSaldoConta().subtract(transacaoVO.getValorTransacao()));

		transacaoDAO.removerTransacao(transacaoVO);

		contaDAO.atualizaConta(contaVO);

	}

}
