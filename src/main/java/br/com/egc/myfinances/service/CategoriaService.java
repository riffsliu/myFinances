package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.CentroCustoDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.CentroCustoVO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;
import br.com.egc.myfinances.util.Message;

@Transactional
public class CategoriaService implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Inject
  private CategoriaDAO categoriaDAO;

  @Inject
  private CentroCustoDAO centroCustoDAO;

  public void criarCategoriaDefault() {

    List<String> listRendas = new ArrayList<>();
    listRendas.add(CategoriaVO.SALARIO);

    List<String> listDespesas = new ArrayList<>();
    listDespesas.add(CategoriaVO.PADRAO);
    listDespesas.add(CategoriaVO.EDUCACAO_MARIDO);
    listDespesas.add(CategoriaVO.EDUCACAO_ESPOSA);
    listDespesas.add(CategoriaVO.EDUCACAO_FILHO1);
    listDespesas.add(CategoriaVO.ALIMENTACAO_TRABALHO);
    listDespesas.add(CategoriaVO.ALIMENTACAO_CASA);
    listDespesas.add(CategoriaVO.SAUDE);
    listDespesas.add(CategoriaVO.TAXA_BANCARIA);
    listDespesas.add(CategoriaVO.CARTAO);
    listDespesas.add(CategoriaVO.EMPRESTIMO);
    listDespesas.add(CategoriaVO.SEGURO_VEICULO);
    listDespesas.add(CategoriaVO.FINANCIAMENTO_VEICULO);
    listDespesas.add(CategoriaVO.INTERNET);
    listDespesas.add(CategoriaVO.LUZ);
    listDespesas.add(CategoriaVO.IPTU);
    listDespesas.add(CategoriaVO.CONDOMINIO);
    listDespesas.add(CategoriaVO.ROUPAS);

    for (String string : listDespesas) {

      CategoriaVO categoriaVO = new CategoriaVO();
      categoriaVO.setDescricaoCategoria(string);
      categoriaVO.setTipoCategoriaEnum(TipoCategoriaEnum.DESPESAS);

      categoriaVO.setCentroCustoVO(centroCustoDAO.buscaCentroCustoPorId(CentroCustoVO.PADRAO1));

      categoriaDAO.criarCategoria(categoriaVO);

    }

    for (String string : listRendas) {

      CategoriaVO categoriaVO = new CategoriaVO();
      categoriaVO.setDescricaoCategoria(string);
      categoriaVO.setTipoCategoriaEnum(TipoCategoriaEnum.RENDAS);

      categoriaVO.setCentroCustoVO(centroCustoDAO.buscaCentroCustoPorId(CentroCustoVO.PADRAO1));

      categoriaDAO.criarCategoria(categoriaVO);

    }

  }

  public void salvarCategoria(CategoriaVO categoriaVO) {

    // categoriaVO.setTipoCategoriaEnum(TipoCategoriaEnum.DESPESAS);

    CategoriaPK categoriaPK = new CategoriaPK(1L, categoriaDAO.nextIdCategoria(1L));
    categoriaPK.setIdUsuario(1L);
    categoriaVO.setCategoriaPK(categoriaPK);

    categoriaVO.setCentroCustoVO(centroCustoDAO.buscaCentroCustoPorId(CentroCustoVO.PADRAO1));

    categoriaDAO.criarCategoria(categoriaVO);

  }

  public List<CategoriaVO> listarCategoria() {

    return categoriaDAO.listarCategoria();
  }

  public List<CategoriaVO> listarCategoriaDespesas() {

    return categoriaDAO.listarCategoriaDespesas();
  }

  public List<CategoriaVO> listarCategoriaRendas() {

    return categoriaDAO.listarCategoriaRendas();
  }

  public void atualizarCategoria(CategoriaVO categoriaVO) {
    categoriaDAO.atualizarCategoria(categoriaVO);

  }

  public void excluirCategoria(CategoriaVO categoriaVO) {

    categoriaVO = categoriaDAO.buscarCategoriaPorId(categoriaVO.getCategoriaPK());

    categoriaDAO.excluirCategoria(categoriaVO);

  }

  public void adicionarCategoria(CategoriaVO categoriaVO) throws Exception {
    
    if (StringUtils.isEmpty(categoriaVO.getDescricaoCategoria()) || StringUtils.isEmpty(categoriaVO.getDescricaoResumida())) {
      throw new Exception(Message.VALORES_OBRIGATORIOS);

    }
    
    if (categoriaVO.getCategoriaPK() == null) {
      salvarCategoria(categoriaVO);

    } else {
      atualizarCategoria(categoriaVO);

    }

  }
  
  public CategoriaVO buscarCategoriaPorId(CategoriaPK categoriaPK) {

    return categoriaDAO.buscarCategoriaPorId(categoriaPK);

  }

}
