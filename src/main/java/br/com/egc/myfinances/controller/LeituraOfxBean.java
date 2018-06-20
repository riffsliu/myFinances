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

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.CategoriaService;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.LeitorOfxService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;
import net.sf.ofx4j.io.OFXParseException;

@SessionScoped
@Named
public class LeituraOfxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LeitorOfxService leitorOfxService;

	@Inject
	private TransacaoService transacaoService;

	@Inject
	private CategoriaService categoriaService;

	@Getter
	private ContaVO contaVO;

	@Getter
	@Setter
	private List<ResumoDTO> listResumoDTO;

	@Getter
	@Setter
	private List<TransacaoVO> listTransacaoVO;

	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaDespesas;
	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaRendas;

	@Getter
	@Setter
	private UploadedFile file;

	@PostConstruct
	public void init() {

		listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
		listCategoriaRendas = categoriaService.listarCategoriaRendas();

	}

	public void initLeituraOfx() {
		System.out.println("LeituraOfxBean.initLeituraOfx()");
		listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
		listCategoriaRendas = categoriaService.listarCategoriaRendas();
		
	}
	

	public Boolean renderizaListaDespesa(TransacaoVO transacaoVO) {

		if (transacaoVO != null) {
			if (transacaoVO.getTipoTransacao().equalsIgnoreCase("DEBIT")) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}

		} else {
			return Boolean.FALSE;

		}

	}

	public void upload() {
		if (file != null) {

			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			try {

				listTransacaoVO = leitorOfxService.processarArquivoOfx(file.getInputstream());

			} catch (IOException | OFXParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void listenerSalvar() {

		transacaoService.adicionarListaTransacao(listTransacaoVO);

		listTransacaoVO.clear();

	}

}
