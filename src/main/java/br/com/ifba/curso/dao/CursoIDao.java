package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericIDao;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Bruno
 */

public interface CursoIDao extends GenericIDao<Curso, Long> {
    // A interface agora define que o método precisa do EntityManager
    List<Curso> findByNome(String nome, EntityManager entityManager);
}