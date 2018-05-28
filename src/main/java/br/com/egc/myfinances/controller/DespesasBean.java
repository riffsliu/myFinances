package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class DespesasBean implements Serializable {

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

		listTransacaoVO = transacaoService.listarTransacaoDespesas();
		listCategoriaVO = categoriaService.listarCategoriaDespesas();

	}

	public void atualizarTransacao() {

		transacaoService.atualizarTransacao(listTransacaoVO);

		listTransacaoVO.clear();

		listTransacaoVO = transacaoService.listarTransacaoDespesas();

	}

}
