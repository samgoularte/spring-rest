package br.com.localhost.dao;

import br.com.localhost.domain.Cursos;
import br.com.localhost.exception.NaoExisteDaoException;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CursosDaoImpl implements CursosDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Cursos curso) {
		entityManager.persist(curso);
	}

	@Override
	public void update(Cursos curso) {
		entityManager.merge(curso);
	}

	@Override
	public void delete(Long id) {
		try {
			entityManager.remove(entityManager.getReference(Cursos.class, id));
		} catch (EntityNotFoundException ex) {
			throw new NaoExisteDaoException("Curso não encontrado para o id = " + id);
		}
	}

	@Override
	public Cursos findById(Long id) {
		Cursos curso = entityManager.find(Cursos.class, id);
		if (curso == null) {
			throw new NaoExisteDaoException("Curso não encontrado para o id = " + id);
		}
		return curso;
	}

	@Override
	public List<Cursos> findAll() {
		return entityManager.createNamedQuery("Cursos.findAll", Cursos.class).getResultList();
	}

	@Override
	public List<Cursos> findAllWithoutVideoaulas() {
		return entityManager.createNamedQuery("Cursos.findAllWithoutVideoaulas", Cursos.class).getResultList();
	}
}
