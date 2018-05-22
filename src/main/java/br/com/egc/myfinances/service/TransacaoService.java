package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.entity.TransacaoVO;

@Transactional
public class TransacaoService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TransacaoDAO transacaoDAO;

	public void adicionarListaTransacao(List<TransacaoVO> listTransacaoVO) {
		
		for (TransacaoVO transacaoVO : listTransacaoVO) {
			
			transacaoDAO.criaTransacao(transacaoVO);
			
		}
		
	}

}
