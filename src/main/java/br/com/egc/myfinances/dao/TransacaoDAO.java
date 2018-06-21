package br.com.egc.myfinances.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.egc.myfinances.dto.ResumoDTO;
import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.TransacaoVO;

public class TransacaoDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransacaoVO buscarTransacaoPorId(Long primaryKey) {

		return getEntityManager().find(TransacaoVO.class, primaryKey);

	}

	@SuppressWarnings("unchecked")
	public List<TransacaoVO> listarTransacaoPorConta(ContaVO contaVO) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.contaVO = :pConta ");

		Query query = getEntityManager().createQuery(sql.toString());

		query.setParameter("pConta", contaVO);

		return query.getResultList();

	}

	public void criaTransacao(TransacaoVO transacaoVO) {

		getEntityManager().persist(transacaoVO);

	}

	public Boolean existeTransacao(Long idTransacaoOriginal) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.idTransacaoOriginal = :pidTransacaoOriginal ");

		Query query = getEntityManager().createQuery(sql.toString());

		query.setParameter("pidTransacaoOriginal", idTransacaoOriginal);

		TransacaoVO transacaoVO = null;
		try {

			transacaoVO = (TransacaoVO) query.getSingleResult();
		} catch (NoResultException noResultException) {

		}

		if (transacaoVO == null) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;

		}

	}

	public List<TransacaoVO> listarTransacaoCategoriaDefault() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.idCategoria=1 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();

	}

	public void atualizaTransacao(TransacaoVO transacaoVO) {

		getEntityManager().merge(transacaoVO);

	}

	public List<TransacaoVO> listarTransacaoRendas() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=1 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}

	public List<TransacaoVO> listarTransacaoDespesas() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT transacaoVO FROM TransacaoVO transacaoVO ");
		sql.append("WHERE transacaoVO.categoriaVO.tipoCategoriaEnum=0 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}
	
	
	public List<ResumoDTO> listarResumo(String ano){
		
		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" select"); 
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
		sb.append(" from newtransacao t inner join newcategoria cat on t.idcategoria=cat.idcategoria and t.idusuario=cat.idusuario");
		sb.append(" group by"); 
		sb.append(" cat.idcategoria");
		sb.append(" order by cat.idtipocategoria desc");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		
		List<Object[]> result = query.getResultList();
		
		List<ResumoDTO> listResumoDTO = new ArrayList<>();
		
		for (Object[] objects : result) {
			
			ResumoDTO resumoDTO = new ResumoDTO();
			
			resumoDTO.setDescricaoCategoria((String) objects[0]);
			resumoDTO.setMes01((BigDecimal) objects[1]);
			resumoDTO.setMes02((BigDecimal) objects[2]);
			resumoDTO.setMes03((BigDecimal) objects[3]);
			resumoDTO.setMes04((BigDecimal) objects[4]);
			resumoDTO.setMes05((BigDecimal) objects[5]);
			resumoDTO.setMes06((BigDecimal) objects[6]);
			resumoDTO.setMes07((BigDecimal) objects[7]);
			resumoDTO.setMes08((BigDecimal) objects[8]);
			resumoDTO.setMes09((BigDecimal) objects[9]);
			resumoDTO.setMes10((BigDecimal) objects[10]);
			resumoDTO.setMes11((BigDecimal) objects[11]);
			resumoDTO.setMes12((BigDecimal) objects[12]);
			
			listResumoDTO.add(resumoDTO);
			
			
		}
		

		return listResumoDTO;

		
		
		
		
		
	}
	
	

}
