package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.egc.myfinances.dao.ContaDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.ContaPK;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.util.Message;

@Transactional
public class ContaService implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Inject
  private ContaDAO contaDAO;

  public List<ContaVO> listarConta() {

    return contaDAO.listarConta();

  }

  public void adicionarConta(ContaVO contaVO) throws Exception {

    if (contaVO.getContaPK() == null) {

      if (StringUtils.isEmpty(contaVO.getNomeConta()) || StringUtils.isEmpty(contaVO.getDescricaoConta()) || contaVO.getDataSaldoInicial() == null
          || contaVO.getSaldoInicial() == null || contaVO.getSaldoAtual() == null) {
        throw new Exception(Message.VALORES_OBRIGATORIOS);

      }

      ContaPK contaPK = new ContaPK(1L, contaDAO.nextIdConta(1L));
      contaPK.setIdUsuario(1L);
      contaVO.setContaPK(contaPK);

      contaDAO.adicionarConta(contaVO);
    } else {
      contaDAO.atualizaConta(contaVO);

    }

  }

  public void excluirConta(ContaVO contaVO) {

    contaVO = contaDAO.buscarContaPorId(contaVO.getContaPK());

    contaDAO.excluirConta(contaVO);

  }

}
