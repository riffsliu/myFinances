package br.com.egc.myfinances.service;

import java.io.Serializable;
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

}
