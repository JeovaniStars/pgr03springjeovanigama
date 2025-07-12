package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext; // Anotação para injetar o EntityManager
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository; // Anotação do Spring

@Repository // 1. Marca a classe como um Bean da camada de repositório
public class CursoDao implements CursoIDao {

    // 2. O Spring vai injetar o EntityManager que ele gerencia
    @PersistenceContext
    private EntityManager entityManager;
    
    // O construtor vazio não é mais estritamente necessário se não houver lógica nele,
    // mas pode ser mantido. A chamada super(Curso.class) era do seu GenericDao.
    public CursoDao() {
    }

    @Override
    public void save(Curso curso) {
        this.entityManager.persist(curso);
    }

    @Override
    public void update(Curso curso) {
        this.entityManager.merge(curso);
    }

    @Override
    public void delete(Curso curso) {
        // Para deletar, o objeto precisa estar no estado "managed"
        this.entityManager.remove(this.entityManager.contains(curso) ? curso : this.entityManager.merge(curso));
    }

    @Override
    public Curso findById(Long id) {
        return this.entityManager.find(Curso.class, id);
    }

    @Override
    public List<Curso> findAll() {
        return this.entityManager.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }
    
    @Override
    public List<Curso> findByNome(String nome) {
        // 3. A lógica usa o EntityManager injetado
        String jpql = "SELECT c FROM Curso c WHERE lower(c.nome) LIKE lower(:nome)";
        TypedQuery<Curso> query = this.entityManager.createQuery(jpql, Curso.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}