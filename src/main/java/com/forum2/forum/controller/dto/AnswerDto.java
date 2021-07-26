package com.forum2.forum.controller.dto;

import java.time.LocalDateTime;

import com.forum2.forum.modelo.Resposta;

public class AnswerDto {

    private Long id;
    private String message;
    private LocalDateTime createdDate;
    private String authorName;
    
    public AnswerDto(Resposta answer){
        this.id = answer.getId();
        this.message = answer.getMensagem();
        this.createdDate = answer.getDataCriacao();
        this.authorName = answer.getAutor().getNome();
    }
    
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public String getAuthorName() {
        return authorName;
    }
}
