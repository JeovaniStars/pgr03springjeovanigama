package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Bruno
 */

@Controller // 1. Anotação para marcar como um Bean do Spring
public class CursoController implements CursoIController {
    
    @Autowired // 2. Injeta a dependência do serviço
    private CursoIService cursoService;

    // A linha "private final CursoIService cursoService = new CursoService();" foi REMOVIDA.

    @Override
    public Curso saveCurso(Curso curso) {
        // A lógica de delegação permanece a mesma, mas agora usa o serviço injetado.
        return this.cursoService.saveCurso(curso);
    }

    @Override
    public Curso updateCurso(Curso curso) {
        return this.cursoService.updateCurso(curso);
    }

    @Override
    public void deleteCurso(Curso curso) {
        this.cursoService.deleteCurso(curso);
    }

    @Override
    public List<Curso> findAllCursos() {
        return this.cursoService.findAllCursos();
    }
    
    @Override
    public List<Curso> findByNome(String nome) {
        return this.cursoService.findByNome(nome);
    }
}