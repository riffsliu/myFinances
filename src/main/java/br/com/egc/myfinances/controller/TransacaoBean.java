package br.com.egc.myfinances.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import br.com.egc.myfinances.service.TransacaoService;
import br.com.egc.myfinances.util.Message;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class TransacaoBean extends BaseBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TransacaoService transacaoService;

  @Getter
  private ContaVO contaVO;

  @Getter
  @Setter
  private List<TransacaoVO> listTransacaoVO;

  private Calendar calendarAtual;

  @Getter
  @Setter
  private String mesAnoSelecionado;

  public void actionTransactions() {

    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");
      calendarAtual = Calendar.getInstance();
      mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());
      listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);
      contaVO = getContaDaSessao();
      redirect("transactions.xhtml");
    } catch (Exception e) {
      addErrorMessage(e.getMessage());
    }

  }

  public void listenerMesAnterior() {

    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

      calendarAtual.add(Calendar.MONTH, -1);

      mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

      listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);
    } catch (Exception e) {
      addErrorMessage(e.getMessage());
    }

  }

  public void listenerMesProximo() {

    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-YYYY");

      calendarAtual.add(Calendar.MONTH, 1);

      mesAnoSelecionado = simpleDateFormat.format(calendarAtual.getTime());

      listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);
    } catch (Exception e) {
      addErrorMessage(e.getMessage());
    }

  }

 

  public Boolean renderizaRemoverTransacao(TransacaoVO transacaoVO) {

    if (transacaoVO.getValorTransacao() == null) {
      return Boolean.FALSE;
    } else {

      return Boolean.TRUE;
    }

  }

  public void listenerRemover(TransacaoVO transacaoVO) {

    try {
      transacaoService.removerTransacao(transacaoVO);
      listTransacaoVO = transacaoService.listarTransacaoTodas(mesAnoSelecionado);
      addInfoMessage(Message.CADASTRO_EXCLUIDO);
    } catch (Exception e) {
      addErrorMessage(e.getMessage());
    }
  }

}
