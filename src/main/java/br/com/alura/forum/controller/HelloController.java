package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// import br.com.alura.forum.repository.UserRepository;

@Controller
public class HelloController {
    // @Autowired
    // UserRepository userRepository;
    @RequestMapping("/")
    @ResponseBody

    public String hello() {
        return "Misera";
    }
}
