package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.CategoriaService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class DespesasBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	List<TransacaoVO> listTransacaoVO;

	@Getter
	@Setter
	List<CategoriaVO> listCategoriaVO;

	@Inject
	private TransacaoService transacaoService;

	@Inject
	private CategoriaService categoriaService;

	@Getter
	@Setter
	private TransacaoVO transacaoVO;

	@Getter
	@Setter
	private String mesAnoSelecionado;

	private Calendar calendarAtual;

	public void init() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual = Calendar.getInstance();

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoDespesas(mesAnoSelecionado);

		listCategoriaVO = categoriaService.listarCategoriaDespesas();

		transacaoVO = new TransacaoVO();

	}

	public void actionDespesas() {
		System.out.println("DespesasBean.actionDespesas()");

		init();

		redirect("newDespesas.xhtml");

	}

	public void listenerMesAnterior() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, -1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoDespesas(mesAnoSelecionado);

	}

	public void listenerMesProximo() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, 1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoDespesas(mesAnoSelecionado);

	}

	public void listenerSalvarDespesa() {
		try {

			transacaoService.adicionarTransacao(transacaoVO);

			transacaoVO = new TransacaoVO();

			addInfoMessage("Adicionado");

		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}

	}

}
