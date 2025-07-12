/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;
/**
 *
 * @author Bruno
 */
public interface CursoIController {
    Curso saveCurso(Curso curso);
    Curso updateCurso(Curso curso);
    void deleteCurso(Curso curso);
    List<Curso> findAllCursos();
    List<Curso> findByNome(String nome);
}
