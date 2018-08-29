package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.egc.myfinances.dto.ConciliacaoDTO;
import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.CategoriaService;
import br.com.egc.myfinances.service.LeitorOfxService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class ProcessarArquivoBean extends BaseBean implements Serializable {

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
	private List<ConciliacaoDTO> listConciliacaoDTO;

	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaDespesas;
	@Getter
	@Setter
	private List<CategoriaVO> listCategoriaRendas;

	@Getter
	@Setter
	private UploadedFile file;

	// @PostConstruct
	// public void init() {
	//
	// listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
	// listCategoriaRendas = categoriaService.listarCategoriaRendas();
	//
	// listConciliacaoDTO = new ArrayList<>();
	//
	// file = null;
	//
	// }
	
	public void actionProcessFile() {
		try {
			
			init();
			redirect("processFile.xhtml");
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}
	}

	private void init() throws Exception{
		
		try {
			
			listCategoriaDespesas = categoriaService.listarCategoriaDespesas();
			
			listCategoriaRendas = categoriaService.listarCategoriaRendas();
			
			listConciliacaoDTO = new ArrayList<>();
			
			file = null;
			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}


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

		try {

			listConciliacaoDTO = leitorOfxService.processarArquivoOfx(file.getInputstream());

			addInfoMessage("Arquivo carregado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}

	}

	public void listenerConciliar(ConciliacaoDTO conciliacaoDTO) {

		try {

			conciliacaoDTO.getTransacaoOfx().setFlagConciliado(Boolean.TRUE);
			transacaoService.adicionarTransacao(conciliacaoDTO.getTransacaoOfx());

			System.out.println("idx"+ conciliacaoDTO.getIndex());
			
			listConciliacaoDTO.remove(conciliacaoDTO);

			addInfoMessage("Conciliado com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}

	}

}
