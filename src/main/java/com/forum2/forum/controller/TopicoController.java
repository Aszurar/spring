package com.forum2.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forum2.forum.controller.dto.DetailsTopicoDto;
// import com.forum2.forum.controller.dto.DetailsTopicoDto;
import com.forum2.forum.controller.dto.TopicoDto;
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
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetailsTopicoDto searchTopicoById(@PathVariable Long id){
        System.out.println("===============================================" + id);
        Topico topico = topicoRepository.getById(id);
        System.out.println("===============================================" + topico);
        return new DetailsTopicoDto(topico);
    }
}
