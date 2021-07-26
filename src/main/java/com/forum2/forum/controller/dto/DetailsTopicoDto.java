package com.forum2.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.forum2.forum.modelo.StatusTopico;
import com.forum2.forum.modelo.Topico;

public class DetailsTopicoDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdDate;
    private String authorName;
    private StatusTopico status;
    private List<AnswerDto> answerList;

    public DetailsTopicoDto(Topico topic) {
        this.id = topic.getId();
        this.title = topic.getTitulo();
        this.message = topic.getMensagem();
        this.createdDate = topic.getDataCriacao();
        this.authorName = topic.getAutor().getNome();
        this.status = topic.getStatus();
        this.answerList = new ArrayList<>();
        this.answerList.addAll(topic.getRespostas().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
    
    public StatusTopico getStatus() {
        return status;
    }

    public List<AnswerDto> getAnswerList() {
        return answerList;
    }

}
