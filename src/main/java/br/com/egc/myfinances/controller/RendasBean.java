package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.CategoriaService;
import br.com.egc.myfinances.service.CentroCustoService;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.LeitorOfxService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;
import net.sf.ofx4j.io.OFXParseException;

@SessionScoped
@Named
public class RendasBean extends BaseBean implements Serializable {

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

	private Calendar calendarAtual;

	@Getter
	@Setter
	private String mesAnoSelecionado;

	public void init() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual = Calendar.getInstance();

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoRendas(mesAnoSelecionado);

	}

	public void actionRendas() {
		System.out.println("RendasBean.actionRendas()");

		init();

		redirect("newRendas.xhtml");

	}

	public void listenerMesAnterior() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, -1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoRendas(mesAnoSelecionado);

	}

	public void listenerMesProximo() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

		calendarAtual.add(Calendar.MONTH, 1);

		mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

		listTransacaoVO = transacaoService.listarTransacaoRendas(mesAnoSelecionado);

	}

	public void atualizarTransacao() {

		// transacaoService.atualizarTransacao(listTransacaoVO);
		//
		// listTransacaoVO.clear();
		//
		// listTransacaoVO = transacaoService.listarTransacaoRendas("05-2018");

	}

}
