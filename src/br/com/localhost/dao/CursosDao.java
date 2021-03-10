package br.com.localhost.dao;

import br.com.localhost.domain.Cursos;
import java.util.List;

public interface CursosDao {

    void save(Cursos curso);

    void update(Cursos curso);

    void delete(Long id);

    Cursos findById(Long id);

    List<Cursos> findAll();
    
    List<Cursos> findAllWithoutVideoaulas();
}
