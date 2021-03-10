package br.com.localhost.dao;

import java.util.List;

import br.com.localhost.domain.Videoaulas;

public interface VideoaulasDao {

	void save(Videoaulas videoaula);

	void update(Videoaulas videoaula);

	void delete(Videoaulas videoaula);

	Videoaulas findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso);

	List<Videoaulas> findAllByCurso(Long idCurso, String fields);

}
