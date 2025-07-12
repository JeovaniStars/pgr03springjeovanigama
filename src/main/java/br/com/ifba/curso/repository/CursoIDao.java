package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

public interface CursoIDao {
    // Note que o parâmetro 'entityManager' foi REMOVIDO de todos os métodos
    void save(Curso curso);
    void update(Curso curso);
    void delete(Curso curso);
    Curso findById(Long id);
    List<Curso> findAll();
    List<Curso> findByNome(String nome);
}