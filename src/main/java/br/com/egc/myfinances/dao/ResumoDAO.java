package br.com.egc.myfinances.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.TipoCategoriaEnum;

public class ResumoDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<ResumoDTO> listarResumo(String ano, TipoCategoriaEnum tipoCategoriaEnum){
		
		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" select"); 
		sb.append(" cat.idcategoria,");
		sb.append(" cat.descricaocategoria,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='01-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes01,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='02-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes02,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='03-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes03,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='04-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes04,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='05-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes05,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='06-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes06,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='07-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes07,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='08-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes08,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='09-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes09,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='10-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes10,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='11-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes11,");
		sb.append(" sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='12-"+ano+"' THEN t.valortransacao ELSE 0 END) AS mes12");
		sb.append(" from newtransacao t inner join newcategoria cat on t.idcategoria=cat.idcategoria and t.idusuariocategoria=cat.idusuario");
		sb.append(" where cat.idtipocategoria= :pTipoCategoria");
		sb.append(" group by"); 
		sb.append(" cat.idcategoria, cat.descricaocategoria, cat.idtipocategoria");
		sb.append(" order by cat.descricaocategoria");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		query.setParameter("pTipoCategoria", tipoCategoriaEnum.getCodigo());
		
		List<Object[]> result = query.getResultList();
		
		List<ResumoDTO> listResumoDTO = new ArrayList<>();
		
		for (Object[] objects : result) {
			
			ResumoDTO resumoDTO = new ResumoDTO();
			
			resumoDTO.setIdCategoria(((BigInteger) objects[0]).longValue());
			resumoDTO.setDescricaoCategoria((String) objects[1]);
			resumoDTO.setMes01((BigDecimal) objects[2]);
			resumoDTO.setMes02((BigDecimal) objects[3]);
			resumoDTO.setMes03((BigDecimal) objects[4]);
			resumoDTO.setMes04((BigDecimal) objects[5]);
			resumoDTO.setMes05((BigDecimal) objects[6]);
			resumoDTO.setMes06((BigDecimal) objects[7]);
			resumoDTO.setMes07((BigDecimal) objects[8]);
			resumoDTO.setMes08((BigDecimal) objects[9]);
			resumoDTO.setMes09((BigDecimal) objects[10]);
			resumoDTO.setMes10((BigDecimal) objects[11]);
			resumoDTO.setMes11((BigDecimal) objects[12]);
			resumoDTO.setMes12((BigDecimal) objects[13]);
			
			listResumoDTO.add(resumoDTO);
			
			
		}
		

		return listResumoDTO;

		
		
		
		
		
	}
	
	

}
