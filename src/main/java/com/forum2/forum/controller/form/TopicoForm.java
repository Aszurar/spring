package com.forum2.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.forum2.forum.modelo.Curso;
import com.forum2.forum.modelo.Topico;
import com.forum2.forum.repository.CursoRepository;

import org.hibernate.validator.constraints.Length;


public class TopicoForm {

    @NotNull @NotEmpty @Length(min=5, max=50)
    private String titulo;

    @NotNull @NotEmpty @Length(min=5, max=50)
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getNomeCurso() {
        return nomeCurso;
    }
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
