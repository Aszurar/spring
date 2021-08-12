package com.forum2.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.forum2.forum.modelo.Topico;
import com.forum2.forum.repository.TopicoRepository;

import org.hibernate.validator.constraints.Length;

public class UpdateTopicForm {

    @NotNull @NotEmpty @Length(min=5, max=50)
    private String title;

    @NotNull @NotEmpty @Length(min=5, max=500)
    private String message;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topico update(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getById(id);

        topico.setTitulo(this.title);
        topico.setMensagem(this.message);

        return topico;
    }
}
