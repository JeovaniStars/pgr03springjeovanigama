package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.JPAUtil; // Importe o seu utilitário
import jakarta.persistence.EntityManager; // Importe o EntityManager
import java.util.List;

public class CursoService implements CursoIService {

    private static final String NOME_OBRIGATORIO = "O campo 'Nome' é obrigatório.";
    private static final String CURSO_NULL = "O objeto Curso não pode ser nulo.";
    private static final String ID_OBRIGATORIO_UPDATE = "Curso não existente. O ID é obrigatório para atualização.";

    private final CursoIDao cursoDao = new CursoDao();

    @Override
    public Curso saveCurso(Curso curso) {
        // Validações de negócio primeiro
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException(NOME_OBRIGATORIO);
        }

        // Início do gerenciamento da persistência
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            entityManager.getTransaction().begin();
            // Passa o EntityManager para o DAO
            this.cursoDao.save(curso, entityManager);
            entityManager.getTransaction().commit();
            return curso;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Lançar uma exceção mais específica seria o ideal
            throw new RuntimeException("Erro ao salvar o curso no banco de dados.", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Curso updateCurso(Curso curso) {
        // Validações
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        if (curso.getId() == null) {
            throw new IllegalArgumentException(ID_OBRIGATORIO_UPDATE);
        }
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException(NOME_OBRIGATORIO);
        }

        // Gerenciamento da persistência (similar ao save)
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            entityManager.getTransaction().begin();
            // O método save do DAO (que usa merge) serve tanto para salvar quanto para atualizar
            this.cursoDao.save(curso, entityManager);
            entityManager.getTransaction().commit();
            return curso;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar o curso no banco de dados.", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            entityManager.getTransaction().begin();
            this.cursoDao.delete(curso, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar o curso no banco de dados.", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Curso> findAllCursos() {
        // Métodos de busca não precisam de transação, apenas do EntityManager
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            return this.cursoDao.findAll(entityManager);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    
    @Override
    public List<Curso> findByNome(String nome) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            return this.cursoDao.findByNome(nome, entityManager);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}