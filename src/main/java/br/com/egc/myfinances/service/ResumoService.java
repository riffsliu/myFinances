package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.ResumoDAO;
import br.com.egc.myfinances.dto.ResumoDTO;

@Transactional
public class ResumoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ResumoDAO resumoDAO;

	
	public List<ResumoDTO> listarResumoDTO(String ano) {

		return resumoDAO.listarResumo(ano);

	}

}
