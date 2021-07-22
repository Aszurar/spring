package com.forum2.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.forum2.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);

    List<Topico> findByTitulo(String titulo);

}