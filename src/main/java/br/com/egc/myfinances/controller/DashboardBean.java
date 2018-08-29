package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.service.DashboardService;
import br.com.egc.myfinances.service.Util;
import br.com.egc.myfinances.util.Message;
import lombok.Getter;

@SessionScoped
@Named
public class DashboardBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private ContaVO contaVO;
	@Getter
	private BigDecimal saldoAtual;
	@Getter
	private BigDecimal totalDespesas;
	@Getter
	private BigDecimal totalRendas;
	@Getter
	private BigDecimal balanco;

	@Inject
	private DashboardService dashboardService;

	public void initDashboard() {

		List<ContaVO> listContaVO = dashboardService.listarConta();

		if (!listContaVO.isEmpty()) {

			contaVO = dashboardService.listarConta().get(0);

			saldoAtual = contaVO.getSaldoAtual();

			String mesAnoSelecionado;
			Calendar calendarAtual;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

			calendarAtual = Calendar.getInstance();

			mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

			totalDespesas = dashboardService.buscarTotalDespesas(mesAnoSelecionado);
			totalRendas = dashboardService.buscarTotalRendas(mesAnoSelecionado);
			balanco = BigDecimal.ZERO;
			balanco = totalRendas.add(totalDespesas);

			Util.setContaNaSession(contaVO);

		} else {

			saldoAtual = BigDecimal.ZERO;
			totalDespesas = BigDecimal.ZERO;
			totalRendas = BigDecimal.ZERO;
			balanco = BigDecimal.ZERO;

		}

	}

	public void actionDashboard() {

		redirect("dashboard.xhtml");

	}

}
