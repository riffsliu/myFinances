package br.com.egc.myfinances.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;

public class CategoriaDAO extends BaseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaVO buscarCategoriaPorId(CategoriaPK categoriaPK) {

		return getEntityManager().find(CategoriaVO.class, categoriaPK);

	}

	public void criaCategoria(CategoriaVO categoriaVO) {

		getEntityManager().persist(categoriaVO);

	}

	public List<CategoriaVO> listarCategoria() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT categoriaVO FROM CategoriaVO categoriaVO ");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		return query.getResultList();
	}
	
	public List<CategoriaVO> listarCategoriaDespesas() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT categoriaVO FROM CategoriaVO categoriaVO ");
		sql.append("WHERE categoriaVO.tipoCategoriaEnum = 0 ");

		Query query = getEntityManager().createQuery(sql.toString());

		return query.getResultList();
	}

	public List<CategoriaVO> listarCategoriaRendas() {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT categoriaVO FROM CategoriaVO categoriaVO ");
		sql.append("WHERE categoriaVO.tipoCategoriaEnum = 1 ");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		return query.getResultList();
	}

	
	
	public Long nextIdCategoria(Long idUsuario) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT max(categoriaVO.categoriaPK.idCategoria) FROM CategoriaVO categoriaVO ");
		sql.append("WHERE categoriaVO.categoriaPK.idUsuario = :pIdUsuario ");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		query.setParameter("pIdUsuario", idUsuario);
		
		Long result = (Long) query.getSingleResult(); 
		
		if(result==null) {
			return 1L;
		}
		
		return result+1;
	}

}
