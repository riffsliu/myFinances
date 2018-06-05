package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.ContaVO;
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
public class TransacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaService contaService;

	@Inject
	private LeitorOfxService leitorOfxService;

	@Inject
	private TransacaoService transacaoService;

	@Inject
	private CategoriaService categoriaService;

	@Inject
	private CentroCustoService centroCustoService;

	//
	@Getter
	private ContaVO contaVO;

	@Getter
	@Setter
	private List<ResumoDTO> listResumoDTO;

	@Getter
	@Setter
	private UploadedFile file;

	public void upload() {
		if (file != null) {

			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			try {

				List<TransacaoVO> listTransacaoVO = leitorOfxService.processarArquivoOfx(file.getInputstream());

				transacaoService.adicionarListaTransacao(listTransacaoVO);

			} catch (IOException | OFXParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void listarConta() {

		contaVO = contaService.listarConta();

	}

	public void populaDefault() {

		contaService.criaContaDefault();
		centroCustoService.criaCentroCustoDefault();
		categoriaService.criarCategoriaDefault();

	}

	public void initResumo() {

		listResumoDTO = transacaoService.listarResumoDTO("2018");

	}

}
