package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.CategoriaService;
import br.com.egc.myfinances.service.CentroCustoService;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.LeitorOfxService;
import br.com.egc.myfinances.service.ResumoService;
import br.com.egc.myfinances.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;
import net.sf.ofx4j.io.OFXParseException;

@SessionScoped
@Named
public class ResumoBean extends BaseBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ResumoService resumoService;

  @Inject
  private TransacaoService transacaoService;

  @Inject
  private CategoriaService categoriaService;

  @Getter
  @Setter
  private List<ResumoDTO> listResumoDTO;

  @Getter
  @Setter
  private List<TransacaoVO> listTransacaoVO;

  @Getter
  @Setter
  private List<CategoriaVO> listCategoriaVO;

  @Getter
  @Setter
  private CategoriaVO categoriaVO;

  @Getter
  @Setter
  private String anoSelecionado;
  @Getter
  @Setter
  private Boolean renderizaDataTablePrimeiroSemestre;
  @Getter
  @Setter
  private Boolean renderizaDataTableSegundoSemestre;

  @Getter
  @Setter
  private String opcaoSemestre;

  @Getter
  @Setter
  private String mesSelecionado;

  private Calendar calendarAtual;

  private Long idCategoria;

  public void init() {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");

    calendarAtual = Calendar.getInstance();

    anoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

    listResumoDTO = resumoService.listarNovoResumoDTO(anoSelecionado);
    
    renderizaDataTablePrimeiroSemestre = Boolean.TRUE;
    opcaoSemestre = "1";

  }

  public void actionResumo() {

    init();

    redirect("newResumo.xhtml");

  }

  public void actionSummary() {

    init();

    redirect("summary.xhtml");

  }

  public void actionResumoPorCategoria(Long idCategoria, String mesSelecionado) {

    
    
    this.mesSelecionado = mesSelecionado;

    this.idCategoria = idCategoria;

    CategoriaPK categoriaPK = new CategoriaPK();
    categoriaPK.setIdUsuario(getUsuarioLogado().getIdUsuario());
    categoriaPK.setIdCategoria(idCategoria);

    listTransacaoVO = transacaoService.listarTransacao(mesSelecionado+"-"+this.anoSelecionado, categoriaPK);

    categoriaVO = categoriaService.buscarCategoriaPorId(categoriaPK);

    if (categoriaVO.getTipoCategoriaEnum().equals(TipoCategoriaEnum.DESPESAS)) {
      listCategoriaVO = categoriaService.listarCategoriaDespesas();
    } else {
      listCategoriaVO = categoriaService.listarCategoriaRendas();

    }

    System.out.println("ResumoBean.actionResumoPorCategoria()");

    redirect("summaryByCategory.xhtml");

  }

  public void listenerAnoAnterior() {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");

    calendarAtual.add(Calendar.YEAR, -1);

    anoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

    listResumoDTO = resumoService.listarResumoDTO(anoSelecionado);

  }

  public void listenerAnoProximo() {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");

    calendarAtual.add(Calendar.YEAR, 1);

    anoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

    listResumoDTO = resumoService.listarResumoDTO(anoSelecionado);

  }

  public void listenerAtualizarTransacao() {

    transacaoService.atualizarTransacao(listTransacaoVO);

    CategoriaPK categoriaPK = new CategoriaPK();
    categoriaPK.setIdUsuario(getUsuarioLogado().getIdUsuario());
    categoriaPK.setIdCategoria(idCategoria);

    listTransacaoVO = transacaoService.listarTransacao(mesSelecionado+"-"+anoSelecionado, categoriaPK);

  }

  public String definindoStyleClass(BigDecimal valor) {

    if (valor.compareTo(BigDecimal.ZERO) == -1) {
      return "red";
    }
    if (valor.compareTo(BigDecimal.ZERO) == 1) {
      return "blue";
    }
    if (valor.compareTo(BigDecimal.ZERO) == 0) {
      return "black";
    }

    return "black";

  }

  public void listenerRenderizaDataTablePorSemestre(ValueChangeEvent event) {

    String opcao = (String) event.getNewValue();
    
    if (opcao.equals("1")) {
      renderizaDataTablePrimeiroSemestre = Boolean.TRUE;
      renderizaDataTableSegundoSemestre = Boolean.FALSE;
    }
    if (opcao.equals("2")) {
      renderizaDataTablePrimeiroSemestre = Boolean.FALSE;
      renderizaDataTableSegundoSemestre = Boolean.TRUE;
    }

  }

}
