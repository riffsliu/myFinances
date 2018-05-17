package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.service.ContaService;
import lombok.Getter;

@SessionScoped
@Named
public class TransacaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContaService contaService;
//	
	@Getter
	private ContaVO  contaVO;
	
	@PostConstruct
	public void listarConta(){
		
		 contaVO = contaService.listarConta();
		
		
	}
	
	public void criaConta() {
		
		ContaVO contaVO = new ContaVO();
		contaVO.setNomeConta("caixa");
		contaVO.setDescricaoConta("conta corrente");
		contaVO.setSaldoConta(new BigDecimal("0"));
		
		contaService.criaConta(contaVO);
		
	}

}
