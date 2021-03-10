package br.com.localhost.service;

import br.com.localhost.dao.CursosDao;
import br.com.localhost.domain.Cursos;
import br.com.localhost.exception.IdNaoValidoServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CursosServiceImpl implements CursosService {

	@Autowired
	private CursosDao dao;

	@Override
	public void save(Cursos curso) {
		dao.save(curso);
		if(curso.getVideoaulas() != null) {
			curso.getVideoaulas()
			.parallelStream()
			.forEach(curso::addVideoaula);
		}
	}

	@Override
	public void update(Long id, Cursos curso) {
		curso.setId(idValido(id));
		dao.findById(id);
		dao.update(curso);
	}

	@Override
	public void delete(Long id) {
		dao.delete(idValido(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Cursos findById(Long id) {
		return dao.findById(idValido(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cursos> findAll() {
		return dao.findAll();
	}

	@Override
	public Cursos updateDataInicial(Long id, Date dataInicio) {
		Cursos curso = dao.findById(idValido(id));
		curso.setDataInicio(dataInicio);
		return curso;
	}

	private Long idValido(Long id) {
		if (id <= 0) {
			throw new IdNaoValidoServiceException("Valor do campo id é inválido!");
		}
		return id;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cursos> findAllWithoutVideoaulas() {
		return dao.findAllWithoutVideoaulas();
	}
}
