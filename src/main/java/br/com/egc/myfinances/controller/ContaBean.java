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

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;
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
public class ContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaService contaService;

	@Getter
	@Setter
	private ContaVO contaVO;

	@Getter
	@Setter
	private List<ContaVO> listContaVO;

	@PostConstruct
	public void init() {
		contaVO = new ContaVO();

		listContaVO = contaService.listarConta();

	}

	public void initContas() {
		System.out.println("ContaBean.initContas()");
		listContaVO = contaService.listarConta();
		
	}

	public void listenerSalvarConta() {

		try {

			contaService.salvarConta(contaVO);

			contaVO = new ContaVO();

			listContaVO = contaService.listarConta();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta created.", "Succesful"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error"));
		}

	}

}
