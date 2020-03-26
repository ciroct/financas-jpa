package financas.model.dao;

import javax.persistence.EntityManager;

import financas.util.jpa.JPAEntityManager;

public class DAO<T> {
	private final Class<T> classe;
	private EntityManager manager;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adicionar(T t) {
		manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(t);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public T consultar(Long id) {
		manager = JPAEntityManager.getEntityManager();
		T instancia = manager.find(classe, id);
		manager.close();
		return instancia;
	}

	public void alterar(T t) {
		manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(t);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public boolean excluir(Long id) {
		manager = JPAEntityManager.getEntityManager();
		try {
			T t = manager.find(classe, id);
			if (t == null)
				return false;
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
		return true;
	}
}
