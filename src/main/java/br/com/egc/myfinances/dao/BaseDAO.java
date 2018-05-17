package br.com.egc.myfinances.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDAO {

	@PersistenceContext
	private EntityManager manager;

	public EntityManager getEntityManager() {
		return manager;
	}

}
