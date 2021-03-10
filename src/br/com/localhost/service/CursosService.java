package br.com.localhost.service;

import br.com.localhost.domain.Cursos;
import java.util.Date;
import java.util.List;

public interface CursosService {

    void save(Cursos curso);

    void update(Long id, Cursos curso);

    void delete(Long id);

    Cursos findById(Long id);

    List<Cursos> findAll();

    Cursos updateDataInicial(Long id, Date dataInicio);
    
    List<Cursos> findAllWithoutVideoaulas();
}
