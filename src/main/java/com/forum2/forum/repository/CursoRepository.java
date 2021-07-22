package com.forum2.forum.repository;

import com.forum2.forum.modelo.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String cursoNome);
    
}
