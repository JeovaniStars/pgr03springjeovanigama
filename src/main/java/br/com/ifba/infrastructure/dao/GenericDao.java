// Pacote: br.com.ifba.infrastructure.dao
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.infrastructure.util.JPAUtil; 
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

// br.com.ifba.infrastructure.dao.GenericDao
public abstract class GenericDao<T extends PersistenceEntity, ID extends Serializable> implements GenericIDao<T, ID> {
    
    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Os métodos agora recebem o EntityManager como parâmetro
    @Override 
    public T save(T entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override 
    public void delete(T entity, EntityManager entityManager) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
    
    @Override 
    public T findById(ID id, EntityManager entityManager) {
        return entityManager.find(entityClass, id);
    }

    @Override 
    public List<T> findAll(EntityManager entityManager) {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }
}