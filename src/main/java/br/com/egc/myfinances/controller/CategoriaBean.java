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
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	@Getter
	@Setter
	private CategoriaVO categoriaVO;

	@Getter
	@Setter
	private TipoCategoriaEnum[] listTipoCategoriaEnum;

	@PostConstruct
	public void init() {
		categoriaVO = new CategoriaVO();
		
		listTipoCategoriaEnum = TipoCategoriaEnum.values();
	}

	public void criaCategoria() {

		categoriaService.criaCategoria(categoriaVO);

		categoriaVO = new CategoriaVO();

		FacesMessage message = new FacesMessage("Succesful, Categoria created.");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

}
