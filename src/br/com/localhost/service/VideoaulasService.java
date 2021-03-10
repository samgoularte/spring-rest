package br.com.localhost.service;

import java.util.List;

import br.com.localhost.domain.Videoaulas;

public interface VideoaulasService {

	void save(Long idCurso, Videoaulas videoaula);
	
	void update(Long idVideoaula, Long idCurso, Videoaulas videoaula);
	
	void delete(Long idVideoaula, Long idCurso);
	
	Videoaulas findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso);
	
	List<Videoaulas> findAllByCurso(Long idCurso, String fields);
}
