package br.com.egc.myfinances.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoPK;
import br.com.egc.myfinances.entity.TransacaoVO;

public class TransacaoDAO extends BaseDAO {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public TransacaoVO buscarTransacaoPorId(Long primaryKey) {

    return getEntityManager().find(TransacaoVO.class, primaryKey);

  }

  public Long nextIdTransacao(Long idUsuario, Long idConta) {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT max(transacaoVO.transacaoPK.idTransacao) FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.transacaoPK.idUsuario = :pIdUsuario ");
    sql.append("AND transacaoVO.transacaoPK.idConta = :pIdConta ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pIdUsuario", idUsuario);
    query.setParameter("pIdConta", idConta);

    Long result = (Long) query.getSingleResult();

    if (result == null) {
      return 1L;
    }

    return result + 1;
  }

  public void adicionarTransacao(TransacaoVO transacaoVO) {

    getEntityManager().persist(transacaoVO);

  }

  @SuppressWarnings("unchecked")
  public List<TransacaoVO> listarTransacaoPorConta(ContaVO contaVO) {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.contaVO = :pConta ");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pConta", contaVO);

    return query.getResultList();

  }

  public List<TransacaoVO> buscarTransacaoParaConciliacao(BigDecimal valorTransacao, String descricaoTransacao, Date dataTransacao) {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.valorTransacao = :pValorTransacao ");
    sql.append("AND transacaoVO.descricaoTransacao = :pDescricaoTransacao ");
    sql.append("AND transacaoVO.dataTransacao = :pDataTransacao ");
    // sql.append("AND transacaoVO.flagConciliado = :pFlag ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pValorTransacao", valorTransacao);
    query.setParameter("pDescricaoTransacao", descricaoTransacao);
    query.setParameter("pDataTransacao", dataTransacao);
    // query.setParameter("pFlag", Boolean.TRUE);

    /*
     * TransacaoVO transacaoVO = null; try {
     * 
     * return transacaoVO = (TransacaoVO) query.getSingleResult(); } catch
     * (NoResultException noResultException) { return null; } catch
     * (NonUniqueResultException nonUniqueResultException) {
     * 
     * throw new
     * Exception("nonUniqueResultException... Problemas ao buscar transação para conciliação {valor: "
     * + valorTransacao + " descrição: " + descricaoTransacao + " data: " +
     * dataTransacao + "}");
     * 
     * }
     */

    return query.getResultList();

  }

  public void atualizaTransacao(TransacaoVO transacaoVO) {

    getEntityManager().merge(transacaoVO);

  }

  public List<TransacaoVO> listarTransacaoRendas(String mesAnoSelecionado) {
    StringBuilder sql = new StringBuilder();

    sql.append(" SELECT transacaoVO FROM TransacaoVO transacaoVO");
    sql.append(" WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=1");
    sql.append(" AND to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return query.getResultList();
  }

  public List<TransacaoVO> listarTransacaoDespesas(String mesAnoSelecionado) {
    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");
    sql.append("AND to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return query.getResultList();
  }

  public List<TransacaoVO> listarTransacaoTodas(String mesAnoSelecionado) {
    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    // sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");
    sql.append("WHERE to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return query.getResultList();
  }

  public List<TransacaoVO> listarTransacaoTodas() {
    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    // sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");
    // sql.append("WHERE to_char(transacaoVO.dataTransacao,'MM-YYYY') =
    // :pMesAnoSelecionado");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    // query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return query.getResultList();
  }

  public List<TransacaoVO> listarTransacao(String mesAnoSelecionado, CategoriaPK categoriaPK) {
    StringBuilder sql = new StringBuilder();

    sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
    sql.append("WHERE ");
    sql.append("to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado ");
    sql.append("AND transacaoVO.categoriaVO.categoriaPK = :pCategoriaPK ");
    sql.append(" ORDER BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);
    query.setParameter("pCategoriaPK", categoriaPK);

    return query.getResultList();
  }

  public void removerTransacao(TransacaoVO transacaoVO) {

    // getEntityManager().remove(buscarTransacaoPorId(transacaoVO.getTransacaoPK()));
    getEntityManager().remove(buscarTransacaoPorId(transacaoVO.getIdTransacao()));

  }

  public BigDecimal buscarTotalDespesas(String mesAnoSelecionado) {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT sum(transacaoVO.valorTransacao) FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");
    sql.append("AND to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado");
    // sql.append(" GROUP BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return (BigDecimal) query.getSingleResult();
  }

  public BigDecimal buscarTotalRendas(String mesAnoSelecionado) {
    StringBuilder sql = new StringBuilder();

    sql.append("SELECT sum(transacaoVO.valorTransacao) FROM TransacaoVO transacaoVO ");
    sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=1 ");
    sql.append("AND to_char(transacaoVO.dataTransacao,'MM-YYYY') = :pMesAnoSelecionado");
    // sql.append(" GROUP BY transacaoVO.dataTransacao, transacaoVO.idTransacao ");

    Query query = getEntityManager().createQuery(sql.toString());

    query.setParameter("pMesAnoSelecionado", mesAnoSelecionado);

    return (BigDecimal) query.getSingleResult();

  }

}
