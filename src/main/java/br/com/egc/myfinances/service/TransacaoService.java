package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	private ContaService contaService;

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

	public List<TransacaoVO> listarTransacaoTodas(String mesAnoSelecionado) throws Exception {

		// List<TransacaoVO> list =
		// transacaoDAO.listarTransacaoTodas(mesAnoSelecionado);
		List<TransacaoVO> list = transacaoDAO.listarTransacaoTodas();

		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(Util.getUsuarioNaSession().getEmailUsuario());

		ContaVO contaVO = contaDAO.buscarContaPorId(Util.getContaNaSession().getContaPK());

		List<TransacaoVO> listNova = new ArrayList<>();
		int contador = 0;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		BigDecimal saldoDia = BigDecimal.ZERO;

		TransacaoVO tSaldoInicial = new TransacaoVO();
		tSaldoInicial.setDataTransacao(contaVO.getDataSaldoInicial());
		tSaldoInicial.setDescricaoTransacao("SALDO DO DIA");
		tSaldoInicial.setSaldoDia(contaVO.getSaldoInicial());
		tSaldoInicial.setValorTransacao(contaVO.getSaldoInicial());
		saldoDia = contaVO.getSaldoInicial();

		for (int i = 0; i < list.size(); i++) {

			TransacaoVO transacaoAtual = list.get(i);

			if (contador == 0) {

				listNova.add(tSaldoInicial);

				saldoDia = saldoDia.add(transacaoAtual.getValorTransacao());

				listNova.add(transacaoAtual);

			} else {

				TransacaoVO transacaoAnterior = list.get(i - 1);

				String dataBaseRegistroAnterior = simpleDateFormat.format(transacaoAnterior.getDataTransacao());

				if (simpleDateFormat.format(transacaoAtual.getDataTransacao()).equals(dataBaseRegistroAnterior)) {

					saldoDia = saldoDia.add(transacaoAtual.getValorTransacao());

					listNova.add(transacaoAtual);
				} else {

					TransacaoVO tSaldo = new TransacaoVO();
					tSaldo.setDataTransacao(transacaoAnterior.getDataTransacao());
					tSaldo.setDescricaoTransacao("SALDO DO DIA");
					tSaldo.setSaldoDia(saldoDia);

					listNova.add(tSaldo);

					saldoDia = saldoDia.add(transacaoAtual.getValorTransacao());

					listNova.add(transacaoAtual);
				}
			}

			contador++;

		}

		simpleDateFormat = new SimpleDateFormat("MM-YYYY");
		List<TransacaoVO> listNova2 = new ArrayList<>();
		for (TransacaoVO transacaoVO : listNova) {

			if (simpleDateFormat.format(transacaoVO.getDataTransacao()).equals(mesAnoSelecionado)) {
				listNova2.add(transacaoVO);

			}

		}

		return listNova2;

	}

	public List<TransacaoVO> listarTransacao(String mesAnoSelecionado, CategoriaPK categoriaPK) {

		return transacaoDAO.listarTransacao(mesAnoSelecionado, categoriaPK);
	}

	public void adicionarTransacao(TransacaoVO transacaoVO) throws Exception {
		
		

		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(Util.getUsuarioNaSession().getEmailUsuario());

		ContaVO contaVO = contaDAO.buscarContaPorId(Util.getContaNaSession().getContaPK());

		transacaoVO.setUsuarioVO(usuarioVO);
		transacaoVO.setContaVO(contaVO);

		transacaoVO.setCategoriaVO(categoriaDAO.buscarCategoriaPorId(transacaoVO.getCategoriaVO().getCategoriaPK()));

		contaVO.setSaldoAtual(contaVO.getSaldoAtual().add(transacaoVO.getValorTransacao()));

		transacaoDAO.adicionarTransacao(transacaoVO);

		contaDAO.atualizaConta(contaVO);

	}

	public void removerTransacao(TransacaoVO transacaoVO) throws Exception {

		ContaVO contaVO = contaDAO.buscarContaPorId(Util.getContaNaSession().getContaPK());
		contaVO.setSaldoAtual(contaVO.getSaldoAtual().subtract(transacaoVO.getValorTransacao()));

		transacaoDAO.removerTransacao(transacaoVO);

		contaDAO.atualizaConta(contaVO);

	}
	
	public ContaVO listarConta() {
	  
	  return contaService.listarConta().get(0);
	  
	}

}
