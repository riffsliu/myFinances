package br.com.egc.myfinances.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;

	public EntityManager getEntityManager() {
		return manager;
	}

}
