package br.com.localhost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.localhost.dao.VideoaulasDao;
import br.com.localhost.domain.Videoaulas;

@Service
@Transactional
public class VideoaulasServiceImpl implements VideoaulasService {

	@Autowired
	private VideoaulasDao dao;
	@Autowired
	private CursosService cursoService;

	@Override
	public void save(Long idCurso, Videoaulas videoaula) {
		videoaula.setCurso(cursoService.findById(idCurso));
		dao.save(videoaula);
	}

	@Override
	public void update(Long idVideoaula, Long idCurso, Videoaulas videoaula) {
		videoaula.setId(idVideoaula);
		videoaula.setCurso(findByIdVideoaulaAndIdCurso(idVideoaula, idCurso).getCurso());
		dao.update(videoaula);
	}

	@Override
	public void delete(Long idVideoaula, Long idCurso) {
		dao.delete(findByIdVideoaulaAndIdCurso(idVideoaula, idCurso));
	}

	@Override
	@Transactional(readOnly = true)
	public Videoaulas findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
		return dao.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Videoaulas> findAllByCurso(Long idCurso, String fields) {
		return dao.findAllByCurso(idCurso, fields);
	}

}
