package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.util.Message;
import br.com.egc.myfinances.util.URL;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class ContaBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaService contaService;

	@Getter
	@Setter
	private ContaVO contaVO;

	@Getter
	@Setter
	private List<ContaVO> listContaVO;

	@Getter
	@Setter
	private Boolean renderizaAdicionar;

	private void init() throws Exception {
		contaVO = new ContaVO();
		listContaVO = contaService.listarConta();
		renderizaAdicionar = Boolean.FALSE;

	}

	public void actionBankAccounts() {
		try {
			init();
			redirect(URL.BANK_ACCOUNT);
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	public void listenerPrepararEdicao(ContaVO contaVO) {
		try {
			this.contaVO = contaVO;
			renderizaAdicionar = Boolean.TRUE;
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	public void listenerPrepararExclusao(ContaVO contaVO) {
		try {
			this.contaVO = contaVO;
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	public void listenerInicializarConta() {
		try {
			contaVO = new ContaVO();
			renderizaAdicionar = Boolean.TRUE;
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	public void listenerAdicionarConta() {
		try {
			contaService.adicionarConta(contaVO);
			init();
			addInfoMessage(Message.CADASTRO_REALIZADO);
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}

	}

	public void listenerExcluirConta() {
		try {
			contaService.excluirConta(contaVO);
			init();
			addInfoMessage(Message.CADASTRO_EXCLUIDO);
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
		
	}

	public void listenerCancelarAdicionarConta() {
		try {
			renderizaAdicionar = Boolean.FALSE;
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}

	}

}
