package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
public class RendasBean implements Serializable {

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

	@PostConstruct
	public void init() {

		listTransacaoVO = transacaoService.listarTransacaoRendas();
		listCategoriaVO = categoriaService.listarCategoriaRendas();

	}

	public void atualizarTransacao() {

		transacaoService.atualizarTransacao(listTransacaoVO);

		listTransacaoVO.clear();

		listTransacaoVO = transacaoService.listarTransacaoRendas();

	}

}
