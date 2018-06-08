package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.TransacaoVO;

@Transactional
public class TransacaoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TransacaoDAO transacaoDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	public void adicionarListaTransacao(List<TransacaoVO> listTransacaoVO) {

		for (TransacaoVO transacaoVO : listTransacaoVO) {

			if (!existeTransacao(transacaoVO.getIdTransacaoOriginal())) {

				transacaoVO.setCategoriaVO(categoriaDAO.buscarCategoriaPorId(1L));

				transacaoDAO.criaTransacao(transacaoVO);
			} else {
				System.out.println("idTransacaoOriginal já existe: " + transacaoVO.getIdTransacaoOriginal());
			}

		}

	}

	public Boolean existeTransacao(Long idTransacaoOriginal) {
		return transacaoDAO.existeTransacao(idTransacaoOriginal);
	}

	public List<TransacaoVO> listarTransacaoCategoriaDefault() {

		return transacaoDAO.listarTransacaoCategoriaDefault();

	}

	public void atualizarTransacao(List<TransacaoVO> listTransacaoVO) {

		for (TransacaoVO transacaoVO : listTransacaoVO) {

			transacaoDAO.atualizaTransacao(transacaoVO);

		}

	}

	public List<TransacaoVO> listarTransacaoRendas() {

		return transacaoDAO.listarTransacaoRendas();

	}

	public List<TransacaoVO> listarTransacaoDespesas() {

		return transacaoDAO.listarTransacaoDespesas();

	}

	public List<ResumoDTO> listarResumoDTO(String ano) {

		return transacaoDAO.listarResumo(ano);

	}

}
