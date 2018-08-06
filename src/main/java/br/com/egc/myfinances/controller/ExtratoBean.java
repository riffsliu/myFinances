package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class ExtratoBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	List<TransacaoVO> listTransacaoVO;

	@Getter
	@Setter
	List<CategoriaVO> listCategoriaVO;

	@Inject
	private TransacaoService transacaoService;

	@Getter
	@Setter
	@Inject
	private ContaService contaService;

	@Getter
	@Setter
	private ContaVO contaVO;

	@Getter
	@Setter
	private String mesAnoSelecionado;

	private Calendar calendarAtual;

	public void init() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual = Calendar.getInstance();

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);

		contaVO = contaService.listarConta().get(0);

	}

	public void actionExtrato() {

		init();

		redirect("extrato.xhtml");

	}

	public void listenerMesAnterior() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, -1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);

	}

	public void listenerMesProximo() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, 1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);

	}

	public void listenerRemover(TransacaoVO transacaoVO) {

		transacaoService.removerTransacao(transacaoVO);

		listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);

		contaVO = contaService.listarConta().get(0);
	}

}
