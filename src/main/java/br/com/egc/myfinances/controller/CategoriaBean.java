package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;
import br.com.egc.myfinances.service.CategoriaService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class CategoriaBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	@Getter
	@Setter
	private CategoriaVO categoriaVO;

	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaRendas;

	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaDespesas;

	@Getter
	@Setter
	private TipoCategoriaEnum[] listTipoCategoriaEnum;

	public void init() {
		categoriaVO = new CategoriaVO();

		listTipoCategoriaEnum = TipoCategoriaEnum.values();

		listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
		listCategoriaRendas = categoriaService.listarCategoriaRendas();

	}
	
	public void actionCategories() {
		redirect("categories.xhtml");
	}
	

	public void initCategoriaRendas() {
		System.out.println("CategoriaBean.initCategoriaRendas()");
		listCategoriaRendas = categoriaService.listarCategoriaRendas();

	}

	public void initCategoriaDespesas() {
		System.out.println("CategoriaBean.actionCategoriaDespesas()");
		listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
	}

	public void listenerSalvarCategoriaDespesa() {

		try {

			categoriaVO.setTipoCategoriaEnum(TipoCategoriaEnum.DESPESAS);

			if (categoriaVO.getCategoriaPK() == null) {
				categoriaService.salvarCategoria(categoriaVO);

			} else {
				categoriaService.atualizarCategoria(categoriaVO);

			}

			categoriaVO = new CategoriaVO();

			listCategoriaDespesas = categoriaService.listarCategoriaDespesas();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria created.", "Succesful"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error"));
		}

	}

	public void listenerSalvarCategoriaRenda() {

		try {
			categoriaVO.setTipoCategoriaEnum(TipoCategoriaEnum.RENDAS);

			if (categoriaVO.getCategoriaPK() == null) {
				categoriaService.salvarCategoria(categoriaVO);

			} else {
				categoriaService.atualizarCategoria(categoriaVO);

			}

			categoriaVO = new CategoriaVO();

			listCategoriaRendas = categoriaService.listarCategoriaRendas();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria created.", "Succesful"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error"));
		}

	}

	

	public void listenerPrepararEdicao(CategoriaVO categoriaVO) {

		this.categoriaVO = categoriaVO;

	}

}
