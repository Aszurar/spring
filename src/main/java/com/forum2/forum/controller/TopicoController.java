package com.forum2.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum2.forum.controller.form.TopicoForm;
import com.forum2.forum.modelo.Topico;
import com.forum2.forum.repository.CursoRepository;
import com.forum2.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> list(String nomeCurso, String title) {
        System.out.println("NomeCurso" + nomeCurso);
        System.out.println("title" + title);

        if (nomeCurso == null && title == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else if (nomeCurso != null) {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);            
        } else {
            List<Topico> topicos = topicoRepository.findByTitulo(title);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
    }
}
