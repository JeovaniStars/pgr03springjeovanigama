package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericIDao<T, ID extends Serializable> {

    // O contrato agora especifica que o EntityManager é necessário
    T save(T entity, EntityManager entityManager);

    void delete(T entity, EntityManager entityManager);

    T findById(ID id, EntityManager entityManager);

    List<T> findAll(EntityManager entityManager);
}