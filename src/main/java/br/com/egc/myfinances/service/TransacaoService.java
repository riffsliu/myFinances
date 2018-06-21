package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.controller.BaseBean;
import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.dao.UsuarioDAO;
import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaPK;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.entity.UsuarioVO;

@Transactional
public class TransacaoService extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TransacaoDAO transacaoDAO;

	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Inject
	private ContaDAO contaDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	

	public void adicionarListaTransacao(List<TransacaoVO> listTransacaoVO) {
		
		
		 UsuarioVO usuarioVO =usuarioDAO.buscarUsuario(Util.getUsuarioNaSession().getEmailUsuario());
		
		ContaVO contaVO = contaDAO.buscarContaPorId(new ContaPK(usuarioVO.getIdUsuario(), 1L));
		

		for (TransacaoVO transacaoVO : listTransacaoVO) {

			if (!existeTransacao(transacaoVO.getIdTransacaoOriginal())) {
				
				transacaoVO.setUsuarioVO(usuarioVO);
				transacaoVO.setContaVO(contaVO);

				transacaoVO.setCategoriaVO(categoriaDAO.buscarCategoriaPorId(transacaoVO.getCategoriaVO().getCategoriaPK()));

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
