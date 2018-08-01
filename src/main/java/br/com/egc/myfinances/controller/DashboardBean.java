package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.DashboardService;
import br.com.egc.myfinances.service.Util;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class DashboardBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private ContaVO contaVO;

	@Getter
	private BigDecimal saldoAtual;
	@Getter
	private BigDecimal totalDespesas;
	@Getter
	private BigDecimal totalRendas;
	@Getter
	private BigDecimal balanco;
	
	@Getter
	@Setter
	private LineChartModel areaModel;

	@Inject
	private DashboardService dashboardService;

	public void initDashboard() {

		contaVO = dashboardService.listarConta().get(0);

		saldoAtual = contaVO.getSaldoConta();

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
		
		createAreaModel();

	}

	public void actionDashboard() {

		redirect("dashboard.xhtml");

	}
	
	
	
	
	private void createAreaModel() {
        areaModel = new LineChartModel();
 
        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        areaModel.addSeries(boys);
        areaModel.addSeries(girls);
 
        areaModel.setTitle("Area Chart");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
 
        Axis xAxis = new CategoryAxis("Years");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }

}
