package br.com.localhost.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.localhost.domain.Videoaulas;
import br.com.localhost.exception.NaoExisteDaoException;

@Repository
public class VideoaulasDaoImpl implements VideoaulasDao {

	@PersistenceContext
	private EntityManager entitytManager;
	
	@Override
	public void save(Videoaulas videoaula) {
		entitytManager.persist(videoaula);
		
	}

	@Override
	public void update(Videoaulas videoaula) {
		entitytManager.merge(videoaula);
	}

	@Override
	public void delete(Videoaulas videoaula) {
		entitytManager.remove(videoaula);
	}

	@Override
	public Videoaulas findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
		try {
			return entitytManager
					.createNamedQuery("Videoaulas.findByIdVideoaulasAndIdCurso", Videoaulas.class)
					.setParameter("idVideoaula", idVideoaula)
					.setParameter("idCurso", idCurso)
					.getSingleResult();
		} catch(NoResultException ex) {
			throw new NaoExisteDaoException("Videoaula id = " + idVideoaula + 
					" não encontrada para Curso id = " + idCurso);
		}
	}

	@Override
	public List<Videoaulas> findAllByCurso(Long idCurso, String fields) {
		if(fields.equals("curso")) 
			return entitytManager
					.createNamedQuery("Videoaulas.findByCurso", Videoaulas.class)
					.setParameter("idCurso", idCurso)
					.getResultList();
		else
			return entitytManager
					.createNamedQuery("Videoaulas.findWithoutCurso", Videoaulas.class)
					.setParameter("idCurso", idCurso)
					.getResultList();
	}

}
