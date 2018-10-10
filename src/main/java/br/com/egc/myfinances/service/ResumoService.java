package br.com.egc.myfinances.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.ResumoDAO;
import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;

@Transactional
public class ResumoService implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Inject
  private ResumoDAO resumoDAO;

  public List<ResumoDTO> listarResumoDTO(String ano) {

    List<ResumoDTO> listResumoDTO = new ArrayList<>();

    ResumoDTO resumoRendas = new ResumoDTO();
    resumoRendas.setDescricaoCategoria("Rendas");
    ResumoDTO resumoDespesas = new ResumoDTO();
    resumoDespesas.setDescricaoCategoria("Despesas");

    List<ResumoDTO> listDespesas = resumoDAO.listarResumo(ano, TipoCategoriaEnum.DESPESAS);
    List<ResumoDTO> listRendas = resumoDAO.listarResumo(ano, TipoCategoriaEnum.RENDAS);

    for (ResumoDTO resumoDTO : listRendas) {
      resumoRendas.setTotalMes01(resumoRendas.getTotalMes01().add(resumoDTO.getMes01()));
      resumoRendas.setTotalMes02(resumoRendas.getTotalMes02().add(resumoDTO.getMes02()));
      resumoRendas.setTotalMes03(resumoRendas.getTotalMes03().add(resumoDTO.getMes03()));
      resumoRendas.setTotalMes04(resumoRendas.getTotalMes04().add(resumoDTO.getMes04()));
      resumoRendas.setTotalMes05(resumoRendas.getTotalMes05().add(resumoDTO.getMes05()));
      resumoRendas.setTotalMes06(resumoRendas.getTotalMes06().add(resumoDTO.getMes06()));
      resumoRendas.setTotalMes07(resumoRendas.getTotalMes07().add(resumoDTO.getMes07()));
      resumoRendas.setTotalMes08(resumoRendas.getTotalMes08().add(resumoDTO.getMes08()));
      resumoRendas.setTotalMes09(resumoRendas.getTotalMes09().add(resumoDTO.getMes09()));
      resumoRendas.setTotalMes10(resumoRendas.getTotalMes10().add(resumoDTO.getMes10()));
      resumoRendas.setTotalMes11(resumoRendas.getTotalMes11().add(resumoDTO.getMes11()));
      resumoRendas.setTotalMes12(resumoRendas.getTotalMes12().add(resumoDTO.getMes12()));

    }

    for (ResumoDTO resumoDTO : listDespesas) {
      resumoDespesas.setTotalMes01(resumoDespesas.getTotalMes01().add(resumoDTO.getMes01()));
      resumoDespesas.setTotalMes02(resumoDespesas.getTotalMes02().add(resumoDTO.getMes02()));
      resumoDespesas.setTotalMes03(resumoDespesas.getTotalMes03().add(resumoDTO.getMes03()));
      resumoDespesas.setTotalMes04(resumoDespesas.getTotalMes04().add(resumoDTO.getMes04()));
      resumoDespesas.setTotalMes05(resumoDespesas.getTotalMes05().add(resumoDTO.getMes05()));
      resumoDespesas.setTotalMes06(resumoDespesas.getTotalMes06().add(resumoDTO.getMes06()));
      resumoDespesas.setTotalMes07(resumoDespesas.getTotalMes07().add(resumoDTO.getMes07()));
      resumoDespesas.setTotalMes08(resumoDespesas.getTotalMes08().add(resumoDTO.getMes08()));
      resumoDespesas.setTotalMes09(resumoDespesas.getTotalMes09().add(resumoDTO.getMes09()));
      resumoDespesas.setTotalMes10(resumoDespesas.getTotalMes10().add(resumoDTO.getMes10()));
      resumoDespesas.setTotalMes11(resumoDespesas.getTotalMes11().add(resumoDTO.getMes11()));
      resumoDespesas.setTotalMes12(resumoDespesas.getTotalMes12().add(resumoDTO.getMes12()));

    }

    resumoRendas.setListResumoDTO(listRendas);
    resumoDespesas.setListResumoDTO(listDespesas);

    listResumoDTO.add(resumoRendas);
    listResumoDTO.add(resumoDespesas);

    return listResumoDTO;

  }

  public List<ResumoDTO> listarNovoResumoDTO(String ano) {

    List<ResumoDTO> listResumoDTO = new ArrayList<>();

    ResumoDTO resumoRendas = new ResumoDTO();
    resumoRendas.setMes01(BigDecimal.ZERO);
    resumoRendas.setMes02(BigDecimal.ZERO);
    resumoRendas.setMes03(BigDecimal.ZERO);
    resumoRendas.setMes04(BigDecimal.ZERO);
    resumoRendas.setMes05(BigDecimal.ZERO);
    resumoRendas.setMes06(BigDecimal.ZERO);
    resumoRendas.setMes07(BigDecimal.ZERO);
    resumoRendas.setMes08(BigDecimal.ZERO);
    resumoRendas.setMes09(BigDecimal.ZERO);
    resumoRendas.setMes10(BigDecimal.ZERO);
    resumoRendas.setMes11(BigDecimal.ZERO);
    resumoRendas.setMes12(BigDecimal.ZERO);
    resumoRendas.setDescricaoCategoria("Total Rendas");

    ResumoDTO resumoDespesas = new ResumoDTO();
    resumoDespesas.setMes01(BigDecimal.ZERO);
    resumoDespesas.setMes02(BigDecimal.ZERO);
    resumoDespesas.setMes03(BigDecimal.ZERO);
    resumoDespesas.setMes04(BigDecimal.ZERO);
    resumoDespesas.setMes05(BigDecimal.ZERO);
    resumoDespesas.setMes06(BigDecimal.ZERO);
    resumoDespesas.setMes07(BigDecimal.ZERO);
    resumoDespesas.setMes08(BigDecimal.ZERO);
    resumoDespesas.setMes09(BigDecimal.ZERO);
    resumoDespesas.setMes10(BigDecimal.ZERO);
    resumoDespesas.setMes11(BigDecimal.ZERO);
    resumoDespesas.setMes12(BigDecimal.ZERO);
    resumoDespesas.setDescricaoCategoria("Total Despesas");

    List<ResumoDTO> listDespesas = resumoDAO.listarResumo(ano, TipoCategoriaEnum.DESPESAS);
    List<ResumoDTO> listRendas = resumoDAO.listarResumo(ano, TipoCategoriaEnum.RENDAS);

    for (ResumoDTO resumoDTO : listRendas) {

      resumoRendas.setMes01(resumoRendas.getMes01().add(resumoDTO.getMes01()));
      resumoRendas.setMes02(resumoRendas.getMes02().add(resumoDTO.getMes02()));
      resumoRendas.setMes03(resumoRendas.getMes03().add(resumoDTO.getMes03()));
      resumoRendas.setMes04(resumoRendas.getMes04().add(resumoDTO.getMes04()));
      resumoRendas.setMes05(resumoRendas.getMes05().add(resumoDTO.getMes05()));
      resumoRendas.setMes06(resumoRendas.getMes06().add(resumoDTO.getMes06()));
      resumoRendas.setMes07(resumoRendas.getMes07().add(resumoDTO.getMes07()));
      resumoRendas.setMes08(resumoRendas.getMes08().add(resumoDTO.getMes08()));
      resumoRendas.setMes09(resumoRendas.getMes09().add(resumoDTO.getMes09()));
      resumoRendas.setMes10(resumoRendas.getMes10().add(resumoDTO.getMes10()));
      resumoRendas.setMes11(resumoRendas.getMes11().add(resumoDTO.getMes11()));
      resumoRendas.setMes12(resumoRendas.getMes12().add(resumoDTO.getMes12()));

    }

    listRendas.add(resumoRendas);

    for (ResumoDTO resumoDTO : listDespesas) {

      resumoDespesas.setMes01(resumoDespesas.getMes01().add(resumoDTO.getMes01()));
      resumoDespesas.setMes02(resumoDespesas.getMes02().add(resumoDTO.getMes02()));
      resumoDespesas.setMes03(resumoDespesas.getMes03().add(resumoDTO.getMes03()));
      resumoDespesas.setMes04(resumoDespesas.getMes04().add(resumoDTO.getMes04()));
      resumoDespesas.setMes05(resumoDespesas.getMes05().add(resumoDTO.getMes05()));
      resumoDespesas.setMes06(resumoDespesas.getMes06().add(resumoDTO.getMes06()));
      resumoDespesas.setMes07(resumoDespesas.getMes07().add(resumoDTO.getMes07()));
      resumoDespesas.setMes08(resumoDespesas.getMes08().add(resumoDTO.getMes08()));
      resumoDespesas.setMes09(resumoDespesas.getMes09().add(resumoDTO.getMes09()));
      resumoDespesas.setMes10(resumoDespesas.getMes10().add(resumoDTO.getMes10()));
      resumoDespesas.setMes11(resumoDespesas.getMes11().add(resumoDTO.getMes11()));
      resumoDespesas.setMes12(resumoDespesas.getMes12().add(resumoDTO.getMes12()));

    }

    listDespesas.add(resumoDespesas);

    listResumoDTO.addAll(listRendas);
    listResumoDTO.addAll(listDespesas);

    return listResumoDTO;

  }

}
