package br.com.egc.myfinances.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.entity.ContaVO;

@Transactional
public class ContaService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ContaDAO contaDAO ;

	public ContaVO listarConta() {
		
		ContaVO contaVO = contaDAO.buscarContaPorId(1l);
		
		System.out.println(contaVO.getDescricaoConta());
		
		return contaVO;
		
	}
	
	public void criaConta(ContaVO contaVO) {
		contaDAO.criaConta(contaVO);
	}
	
	

}
