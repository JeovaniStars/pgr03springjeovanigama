package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository; // Importamos o repositório do Spring Data
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 1. Marca a classe como um Bean de Serviço para o Spring
public class CursoService implements CursoIService {

    private static final String NOME_OBRIGATORIO = "O campo 'Nome' é obrigatório.";
    private static final String CURSO_NULL = "O objeto Curso não pode ser nulo.";
    private static final String ID_OBRIGATORIO_UPDATE = "Curso não existente. O ID é obrigatório para atualização.";

    @Autowired // 2. Injeta a dependência do repositório automaticamente
    private CursoRepository cursoRepository;

    @Override
    @Transactional // 3. O Spring gerencia a transação (begin, commit, rollback)
    public Curso saveCurso(Curso curso) {
        // A lógica de validação de negócio permanece a mesma
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException(NOME_OBRIGATORIO);
        }

        // A complexidade da transação desaparece. Apenas delegamos ao repositório.
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Curso updateCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        if (curso.getId() == null) {
            throw new IllegalArgumentException(ID_OBRIGATORIO_UPDATE);
        }
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException(NOME_OBRIGATORIO);
        }
        
        // O método save() do Spring Data JPA serve tanto para salvar quanto para atualizar.
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void deleteCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException(CURSO_NULL);
        }
        cursoRepository.delete(curso);
    }

    @Override
    @Transactional(readOnly = true) // Transação apenas de leitura é mais otimizada
    public List<Curso> findAllCursos() {
        return cursoRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByNome(String nome) {
        // Aqui você implementaria a busca no seu CursoRepository
        // Exemplo: return cursoRepository.findByNomeContainingIgnoreCase(nome);
        // Por enquanto, vamos deixar um placeholder:
        throw new UnsupportedOperationException("Método de busca por nome não implementado no repositório.");
    }
}