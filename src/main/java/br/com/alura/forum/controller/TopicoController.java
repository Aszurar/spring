package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController
public class TopicoController {
    @RequestMapping("/topicos")
    public List<TopicoDto> list() {
        Topico topico = new Topico("Duvida","Duvida com Spring", new Curso("Spring", "Programação"));
        Topico topico1 = new Topico("Duvida","Duvida com React", new Curso("React", "Programação"));
        Topico topico2 = new Topico("Duvida","Duvida com React-Native", new Curso("React-Native", "Programação"));
        
        return TopicoDto.converter(Arrays.asList(topico, topico1, topico2));
    }
}
