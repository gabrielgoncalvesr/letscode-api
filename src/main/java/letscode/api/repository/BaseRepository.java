package letscode.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

public class BaseRepository<T> {

	@PersistenceContext
	protected EntityManager em;

	protected Class<T> classEntity;

	public BaseRepository(Class<T> classEntity) {
		//this.em = em;
		this.classEntity = classEntity;
	}

	public EntityManager getSession() {
		return this.em;
	}

	public T getById(Object id) {
		return em.find(classEntity, id);
	}

	public TypedQuery<T> query(String query) {
		return em.createQuery(query, classEntity);
	}

	public T getUnique(TypedQuery<T> query) {
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@SuppressWarnings("unchecked")
	public T persist(Object id, Object match) {
		if (id == null) {
			em.persist(match);
		} else {
			match = em.merge(match);
		}

		return (T) match;
	}
}