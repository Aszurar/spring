package com.forum2.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.forum2.forum.controller.form.UpdateTopicForm;
import com.forum2.forum.modelo.Topico;
import com.forum2.forum.repository.CursoRepository;
import com.forum2.forum.repository.TopicoRepository;
import org.springframework.web.bind.annotation.PutMapping;


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
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicoDto> searchTopicoById(@PathVariable Long id){
        // System.out.println(id);
        // System.out.println("===============================================");
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = topicoRepository.getById(id);
            return ResponseEntity.ok(new DetailsTopicoDto(topico));
            // System.out.println(topico);
            // System.out.println("===============================================");
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        
        if (optional.isPresent()) {
            Topico topico = form.update(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);

        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
