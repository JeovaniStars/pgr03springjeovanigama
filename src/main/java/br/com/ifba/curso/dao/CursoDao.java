package br.com.ifba.curso.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Bruno
 */

// br.com.ifba.curso.dao.CursoDao

public class CursoDao extends GenericDao<Curso, Long> implements CursoIDao {

    public CursoDao() {
        super(Curso.class);
    }

    @Override   
    public List<Curso> findByNome(String nome, EntityManager entityManager) {
        // A lógica interna do método continua a mesma, excelente!
        String jpql = "SELECT c FROM Curso c WHERE lower(c.nome) LIKE lower(:nome)";
        TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}