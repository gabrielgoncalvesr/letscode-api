package letscode.api.repository;

import javax.persistence.EntityManager;

public class BaseRepository<T> {

	protected EntityManager em;

	public BaseRepository(EntityManager em) {
		this.em = em;
	}
	
	public EntityManager getSession() {
		return this.em;
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